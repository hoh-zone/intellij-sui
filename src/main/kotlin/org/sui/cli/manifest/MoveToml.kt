package org.sui.cli.manifest

import org.sui.cli.manifest.SuiConfigYaml
import com.intellij.openapi.project.Project
import org.sui.cli.*
import org.sui.lang.toNioPathOrNull
import org.sui.openapiext.*
import org.sui.stdext.chain
import org.toml.lang.psi.TomlFile
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.error.YAMLException
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.readText


class MoveToml(
    val project: Project,
    val tomlFile: TomlFile,
    val packageTable: MoveTomlPackageTable? = null,

    val addresses: RawAddressMap = mutableRawAddressMap(),
    val devAddresses: RawAddressMap = mutableRawAddressMap(),

    var deps: List<Pair<TomlDependency, RawAddressMap>> = emptyList(),
    val devDeps: List<Pair<TomlDependency, RawAddressMap>> = emptyList()
) {
    val packageName: String? get() = packageTable?.name

    fun declaredAddresses(): PackageAddresses {
        val packageName = this.packageName ?: ""
        val raws = this.addresses

        val values = mutableAddressMap()
        val placeholders = placeholderMap()
        for ((addressName, addressVal) in raws.entries) {
            val (value, tomlKeyValue) = addressVal
            if (addressVal.first == Consts.ADDR_PLACEHOLDER) {
                placeholders[addressName] = PlaceholderVal(tomlKeyValue, packageName)
            } else {
                values[addressName] = AddressVal(value, tomlKeyValue, null, packageName)
            }
        }
        return PackageAddresses(values, placeholders)
    }

    data class MoveTomlPackageTable(
        val name: String?,
        val version: String?,
        val authors: List<String>,
        val license: String?
    )

    companion object {

        fun fromTomlFile(tomlFile: TomlFile): MoveToml {
            // needs read access for Toml
            checkReadAccessAllowed()

            val packageTomlTable = tomlFile.getTable("package")
            var packageTable: MoveTomlPackageTable? = null
            if (packageTomlTable != null) {
                val name = packageTomlTable.findValue("name")?.stringValue()
                val version = "" //packageTomlTable.findValue("version")?.stringValue()
                val authors:List<String> =  emptyList()  //packageTomlTable.findValue("authors")?.arrayValue()
//                        .orEmpty()
//                        .mapNotNull { it.stringValue() }
                val license = packageTomlTable.findValue("license")?.stringValue()
                packageTable = MoveTomlPackageTable(name, version, authors, license)
            }

            val addresses = parseAddresses("addresses", tomlFile)
            val devAddresses = parseAddresses("dev-addresses", tomlFile)

            val contentRoot = tomlFile.parent?.virtualFile?.toNioPathOrNull()
            var deps = contentRoot?.let { parseDependencies("dependencies", tomlFile, it) }.orEmpty()
//            if(deps.isEmpty()) {
//                val depPair = getYamlByPath(contentRoot, tomlFile.project.name)
//                val dependencies = mutableListOf<Pair<TomlDependency, RawAddressMap>>()
//                if(depPair!=null) {
//                    dependencies.add(depPair)
//                    dependencies.addAll(deps)
//                    deps = dependencies.toList()
//                }
//            }
            val devDeps = contentRoot?.let { parseDependencies("dev-dependencies", tomlFile, it) }.orEmpty()


            return MoveToml(
                tomlFile.project,
                tomlFile,
                packageTable,
                addresses,
                devAddresses,
                deps,
                devDeps
            )
        }

        fun fromBuildInfo(tomlFile: TomlFile,depMoveToml:MoveToml): MoveToml {
            val deps = depMoveToml.deps
            val contentRoot = tomlFile.parent?.virtualFile?.toNioPathOrNull() ?: return depMoveToml
            val projectName = depMoveToml.packageName
            val path = Paths.get(contentRoot.toString(),  "build",projectName, "BuildInfo.yaml")
            path.toVirtualFile()?.toNioPathOrNull()?: return depMoveToml
            val yaml =
                try {
                    Yaml().load<Map<String, Any>>(path.readText())
                } catch (e: YAMLException) {
                    // TODO: error notification?
                    return depMoveToml
                }

            val compiledPackageInfo = (yaml["compiled_package_info"] as? Map<String, *>)?: return depMoveToml
            val buildFlags = (compiledPackageInfo["build_flags"] as? Map<String, *> )?: return depMoveToml

            val implicitDependencies = findGitByName(buildFlags,"implicit_dependencies")?: return depMoveToml
            val dependencies = mutableListOf<Pair<TomlDependency, RawAddressMap>>()
            implicitDependencies.forEach{
                val depName = it.key.lowercase()
                val depMap = it.value as? Map<String, Any>?: return@forEach
                val depPair = parseBuildInfoGitDependency(depMap,depName)?: return@forEach
                dependencies.add(depPair)
            }
            if(!dependencies.isEmpty()){
                dependencies.addAll(deps)
                depMoveToml.deps = dependencies.toList()
            }
            return depMoveToml
        }

        private fun parseAddresses(tableKey: String, tomlFile: TomlFile): RawAddressMap {
            val addresses = mutableMapOf<String, RawAddressVal>()
            val tomlAddresses = tomlFile.getTable(tableKey)?.namedEntries().orEmpty()
            for ((addressName, tomlValue) in tomlAddresses) {
                val value = tomlValue?.stringValue() ?: continue
                addresses[addressName] = Pair(value, tomlValue.keyValue)
            }
            return addresses
        }

        private fun parseDependencies(
            tableKey: String,
            tomlFile: TomlFile,
            projectRoot: Path
        ): List<Pair<TomlDependency, RawAddressMap>> {

            val tomlInlineTableDeps = tomlFile.getTable(tableKey)
                ?.namedEntries().orEmpty()
                .map { Pair(it.first, it.second?.toMap().orEmpty()) }

            val dependencies = mutableListOf<Pair<TomlDependency, RawAddressMap>>()
            val notHasSui = tomlInlineTableDeps.isEmpty() || tomlInlineTableDeps.filter { it.first == "Sui" }.isEmpty()
//
//            if(notHasSui){
//                val depPair =getYamlByPath(projectRoot,tomlFile.project.name)
//                if(depPair!=null)
//                    dependencies.add(depPair)
//            }
//            if(tomlInlineTableDeps.isEmpty())
//                return dependencies

            val tomlTableDeps = tomlFile
                .getTablesByFirstSegment(tableKey)
                .map { Pair(it.header.key?.segments?.get(1)!!.text, it.toMap()) }

            for ((depName, depMap) in tomlInlineTableDeps.chain(tomlTableDeps)) {
                val depPair = when {
                    depMap.containsKey("local") -> parseLocalDependency(depName, depMap, projectRoot)
                    depMap.containsKey("git") -> parseGitDependency(depName, depMap)
                    else -> null
                } ?: continue

                dependencies.add(depPair)
            }

            return dependencies
        }

        private fun parseLocalDependency(
            depName: String,
            depTable: TomlElementMap,
            projectRoot: Path
        ): Pair<TomlDependency.Local, RawAddressMap>? {
            val localPathValue = depTable["local"]?.stringValue() ?: return null
            val normalizedLocalPath =
                projectRoot.resolve(localPathValue).toAbsolutePath().normalize()
            val subst = parseAddrSubst(depTable)
            return Pair(TomlDependency.Local(depName, normalizedLocalPath), subst)
        }

        private fun parseGitDependency(
            depName: String,
            depTable: TomlElementMap,
        ): Pair<TomlDependency.Git, RawAddressMap>? {
            val repo = depTable["git"]?.stringValue() ?: return null
            val rev = depTable["rev"]?.stringValue() ?: return null
            val subdir = depTable["subdir"]?.stringValue() ?: ""
            val subst = parseAddrSubst(depTable)
            return Pair(
                TomlDependency.Git(
                    depName,
                    repo,
                    rev,
                    subdir,
                ), subst
            )
        }

        private fun parseAddrSubst(depTable: TomlElementMap): RawAddressMap {
            val substEntries =
                depTable["addr_subst"]?.inlineTableValue()?.namedEntries().orEmpty()
            val subst = mutableRawAddressMap()
            for ((name, tomlValue) in substEntries) {
                if (tomlValue == null) continue
                val value = tomlValue.stringValue() ?: continue
                val keyValue = tomlValue.keyValue
                subst[name] = Pair(value, keyValue)
            }
            return subst
        }

//        private fun parseGitSuiBuild(
//            projectRoot: Path,
//            depName: String,
//            projectName:String
//        ): Pair<TomlDependency.Git, RawAddressMap>? {
//
//            val path = Paths.get(projectRoot.toString(),  "build",projectName, "BuildInfo.yaml")
//            val repo = ""
//            val rev = ""
//            val subdir =  ""
//            val subst = mutableRawAddressMap()
//            return Pair(
//                TomlDependency.Git(
//                    depName,
//                    repo,
//                    rev,
//                    subdir,
//                ), subst
//            )
//        }

        private fun parseBuildInfoGitDependency(
            depMap: Map<String, *>,
            depName:String
        ): Pair<TomlDependency.Git, RawAddressMap>?{
//            val path = Paths.get(projectRoot.toString(),  "build",projectName, "BuildInfo.yaml")
//            val yaml =
//                try {
//                    Yaml().load<Map<String, Any>>(path.readText())
//                } catch (e: YAMLException) {
//                    // TODO: error notification?
//                    return null
//                }
//            val compiled_package_info = (yaml["compiled_package_info"] as? Map<String, *>)?: return null
//            val build_flags = (compiled_package_info["build_flags"] as? Map<String, *> )?: return null
//            val Sui = findGitByName(build_flags,"Sui")?: return null
            val gitInfo = findGitByName(depMap,"Git")?: return null

            val gitUrl = gitInfo["git_url"] as? String?: return null
            val gitRev = gitInfo["git_rev"] as? String?: return null
            val gitSubdir = gitInfo["subdir"] as? String?: return null
            val subst = mutableRawAddressMap()
            return Pair(
                TomlDependency.Git(
                    depName,
                    gitUrl,
                    gitRev,
                    gitSubdir,
                ), subst
            )
        }
        private fun findGitByName(
            yaml:Map<String, *>,
            name:String
        ):Map<String,*>?{
            yaml.forEach{
                if(it.key.equals(name)||it.key.equals(name.lowercase()))
                    return it.value as? Map<String, *>?:null

                if(it.value !is Map<*,*>)
                    return@forEach
                val mapValue = it.value as? Map<String,*>?: return@forEach
                return findGitByName(mapValue,name)?: return@forEach
            }
            return null
        }
//
//        private fun parseAddrSubstSui(depTable: TomlElementMap): RawAddressMap {
//            val substEntries =
//                depTable["addr_subst"]?.inlineTableValue()?.namedEntries().orEmpty()
//            val subst = mutableRawAddressMap()
//            for ((name, tomlValue) in substEntries) {
//                if (tomlValue == null) continue
//                val value = tomlValue.stringValue() ?: continue
//                val keyValue = tomlValue.keyValue
//                subst[name] = Pair(value, keyValue)
//            }
//            return subst
//        }



    }
}
