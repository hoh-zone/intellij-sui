import org.jetbrains.intellij.platform.gradle.Constants.Constraints
import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.intellij.platform.gradle.tasks.VerifyPluginTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.ByteArrayOutputStream
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_2
import java.util.*

val publishingToken = System.getenv("JB_PUB_TOKEN") ?: null
val publishingChannel = System.getenv("JB_PUB_CHANNEL") ?: "default"
// set by default in Github Actions
val isCI = System.getenv("CI") != null

fun prop(name: String): String =
    extra.properties[name] as? String
        ?: error("Property `$name` is not defined in gradle.properties for environment `$shortPlatformVersion`")

fun propOrNull(name: String): String? = extra.properties[name] as? String

fun Project.gitCommitHash(): String {
    val byteOut = ByteArrayOutputStream()
    providers.exec {
        commandLine("git", "rev-parse", "--short", "HEAD")
        standardOutput = byteOut
    }.result.get()
    return String(byteOut.toByteArray()).trim().also {
        if (it == "HEAD")
            logger.warn("Unable to determine current branch: Project is checked out with detached head!")
    }
}

fun Project.gitTimestamp(): String {
    val byteOut = ByteArrayOutputStream()
    providers.exec {
        commandLine("git", "show", "--no-patch", "--format=%at", "HEAD")
        standardOutput = byteOut
    }.result.get()
    return String(byteOut.toByteArray()).trim().also {
        if (it == "HEAD")
            logger.warn("Unable to determine current branch: Project is checked out with detached head!")
    }
}

val shortPlatformVersion = prop("shortPlatformVersion")
val useInstaller = prop("useInstaller").toBooleanStrict()
val codeVersion = "2.0"

// Auto-bump buildNumber on every `buildPlugin` invocation so the produced zip
// always carries a fresh version (avoids IDE plugin install caching issues).
val rawBuildNumber = (extra.properties["buildNumber"] as? String)?.toIntOrNull() ?: 1
val isBuildingPlugin = gradle.startParameter.taskNames.any {
    it == "buildPlugin" || it.endsWith(":buildPlugin")
}
val buildNumber = if (isBuildingPlugin) {
    val next = rawBuildNumber + 1
    val gradleProps = rootProject.file("gradle.properties")
    gradleProps.writeText(
        gradleProps.readText().replace(
            Regex("(?m)^buildNumber=.*$"),
            "buildNumber=$next"
        )
    )
    logger.lifecycle("Bumped buildNumber: $rawBuildNumber -> $next")
    next
} else {
    rawBuildNumber
}

var pluginVersion = "$codeVersion.$shortPlatformVersion.$buildNumber"
if (publishingChannel != "default") {
    // timestamp of the commit with this eaps addition
    val start = 1714498465
    val commitTimestamp = project.gitTimestamp().toInt() - start
    pluginVersion = "$pluginVersion-$publishingChannel.$commitTimestamp"
}

val pluginGroup = "org.sui"
val pluginName = "intellij-sui-move"
val pluginJarName = "intellij-sui-move-$pluginVersion"
val javaVersion = JavaVersion.VERSION_21

val kotlinReflectVersion = "2.3.21"

group = pluginGroup
version = pluginVersion

plugins {
    id("java")
    kotlin("jvm") version "2.3.21"
    id("org.jetbrains.intellij.platform") version "2.16.0"
    id("org.jetbrains.grammarkit") version "2023.3.0.3"
    id("net.saliman.properties") version "1.6.0"
    id("org.gradle.idea")
    id("de.undercouch.download") version "5.7.0"
}

allprojects {
    apply {
        plugin("kotlin")
        plugin("org.jetbrains.grammarkit")
        plugin("org.jetbrains.intellij.platform")
        plugin("de.undercouch.download")
    }

    repositories {
        mavenCentral()
        gradlePluginPortal()
        intellijPlatform {
            defaultRepositories()
            jetbrainsRuntime()
        }
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinReflectVersion")

        implementation("io.sentry:sentry:8.41.0") {
            exclude("org.slf4j")
        }
        implementation("org.apache.commons:commons-text:1.15.0")
        implementation("com.github.ajalt.clikt:clikt:5.1.0")
        testImplementation("junit:junit:4.13.2")
        testImplementation("org.opentest4j:opentest4j:1.3.0")

        intellijPlatform {
            // Use intellijIdea() for 2025.3+
            val installerFlag = useInstaller
            intellijIdea(prop("platformVersion")) {
                useInstaller = installerFlag
            }
            testFramework(TestFrameworkType.Platform)
            pluginVerifier(Constraints.LATEST_VERSION)
            bundledPlugin("org.toml.lang")
            jetbrainsRuntime()
        }
    }

//    configure<JavaPluginExtension> {
//        sourceCompatibility = javaVersion
//        targetCompatibility = javaVersion
//    }

    sourceSets {
        main {
            java.srcDirs("src/main/gen")
        }
    }

    kotlin {
        jvmToolchain(21)
        if (file("src/$shortPlatformVersion/main/kotlin").exists()) {
            sourceSets {
                main {
                    kotlin.srcDirs("src/$shortPlatformVersion/main/kotlin")
                }
            }
        }
    }

    intellijPlatform {
        pluginConfiguration {
            version.set(pluginVersion)
            ideaVersion {
                sinceBuild.set(prop("pluginSinceBuild"))
                untilBuild.set(prop("pluginUntilBuild"))
            }
            val codeVersionForUrl = codeVersion.replace('.', '-')
            changeNotes.set(
                """
                    <body>
                        <p><a href="https://intellij-move.github.io/$codeVersionForUrl.html">
                            Changelog for the Intellij-Move $codeVersion
                            </a></p>
                    </body>
                """
            )

        }

        instrumentCode.set(false)

        publishing {
            token.set(publishingToken)
            channels.set(listOf(publishingChannel))
        }

        pluginVerification {
            ides {
                recommended()
            }
            //if("SNAPSHOT"!inshortPlatformVersion){
            //ides{
            //ide(prop("verifierIdeVersion").trim())
            //}
            //}
            failureLevel.set(
                EnumSet.complementOf(
                    EnumSet.of(
                        //these are the only issue swetolerate
                        VerifyPluginTask.FailureLevel.DEPRECATED_API_USAGES,
                        VerifyPluginTask.FailureLevel.EXPERIMENTAL_API_USAGES,
                        VerifyPluginTask.FailureLevel.SCHEDULED_FOR_REMOVAL_API_USAGES,
                    )
                )
            )
        }
    }
    tasks {
        compileKotlin {
            compilerOptions {
                languageVersion.set(KOTLIN_2_2)
                apiVersion.set(KOTLIN_2_2)
                freeCompilerArgs.add("-Xjvm-default=all")
            }
        }

        jar {
            duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        }

        generateLexer {
            sourceFile.set(file("src/main/grammars/MoveLexer.flex"))
            targetOutputDir.set(file("src/main/gen/org/sui/lang"))
//            purgeOldFiles.set(true)
        }
        generateParser {
            sourceFile.set(file("src/main/grammars/MoveParser.bnf"))
            targetRootOutputDir.set(file("src/main/gen"))
            // not used if purgeOldFiles set to false
            pathToParser.set("/org/sui/lang/MoveParser.java")
            pathToPsiRoot.set("/org/sui/lang/core/psi")
//            purgeOldFiles.set(true)
            // Workaround for grammar-kit-plugin 2023.3.0.1 incompatibility with IntelliJ Platform 261+
            // (NoClassDefFoundError: KeyedExtensionCollector). Add platform libs explicitly.
            // See: https://github.com/JetBrains/gradle-grammar-kit-plugin/issues/223
            val requiredLibs261 = listOf(
                "intellij.platform.core",
                "intellij.platform.core.impl",
                "intellij.platform.analysis",
                "intellij.platform.analysis.impl",
                "intellij.platform.projectModel",
                "intellij.platform.lang",
                "intellij.libraries.fastutil",
                "intellij.libraries.kotlinx.coroutines.core",
                "intellij.libraries.kotlinx.collections.immutable",
            )
            val platformLibs = intellijPlatform.platformPath.resolve("lib")
            classpath += fileTree(platformLibs) {
                include { it.file.nameWithoutExtension in requiredLibs261 }
            }
        }

        withType<KotlinCompile> {
            dependsOn(generateLexer, generateParser)
        }

    }

    val runIdeWithPlugins by intellijPlatformTesting.runIde.registering {
        plugins {
            plugin("com.google.ide-perf:1.3.1")
//            plugin("PsiViewer:PsiViewer 241.14494.158-EAP-SNAPSHOT")
        }
        task {
            systemProperty("org.sui.debug.enabled", true)
            systemProperty("org.sui.types.highlight.unknown.as.error", true)
//            systemProperty("org.move.external.linter.max.duration", 30)  // 30 ms
//            systemProperty("idea.log.debug.categories", "org.move.cli")
        }

        prepareSandboxTask {
        }
    }

    tasks.register("resolveDependencies") {
        doLast {
            rootProject.allprojects
                .map { it.configurations }
                .flatMap { it.filter { c -> c.isCanBeResolved } }
                .forEach { it.resolve() }
        }
    }

    idea {
        pathVariables(mapOf("USER_HOME" to file("/Users/yu")))
        module {
            name = "intellij-sui-move.main"
        }
    }
}

val Project.dependencyCachePath
    get(): String {
        val cachePath = file("${rootProject.projectDir}/deps")
        // If cache path doesn't exist, we need to create it manually
        // because otherwise gradle-intellij-plugin will ignore it
        if (!cachePath.exists()) {
            cachePath.mkdirs()
        }
        return cachePath.absolutePath
    }
