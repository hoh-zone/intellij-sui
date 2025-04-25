package org.sui.ide.lsp

//import com.intellij.javascript.nodejs.util.NodePackageRef
//import com.intellij.lang.typescript.lsp.LspServerLoader
//import com.intellij.lang.typescript.lsp.LspServerPackageDescriptor
//import com.intellij.lang.typescript.lsp.PackageVersion
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.registry.Registry
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServer
import com.intellij.platform.lsp.api.LspServerDescriptor
import com.intellij.platform.lsp.api.LspServerManager
import com.intellij.platform.lsp.api.LspServerSupportProvider
import com.intellij.platform.lsp.api.lsWidget.LspServerWidgetItem
import org.sui.lang.MoveFileType

//import com.intellij.platform.lsp.api.Lsp4jClient
//import com.intellij.util.text.SemVer

//import org.intellij.prisma.PrismaIcons
//import org.intellij.prisma.ide.ui.PrismaSettingsConfigurable

class MvSuiLspServerSupportProvider:LspServerSupportProvider{
    override fun fileOpened(project: Project, file: VirtualFile, serverStarter: LspServerSupportProvider.LspServerStarter) {
        serverStarter.ensureServerStarted(MvSuiLspServerDescriptor(project))

    }
}



class MvSuiLspServerDescriptor(project: Project): LspServerDescriptor(project,"move"){
    override fun isSupportedFile(file: VirtualFile) = file.extension == MoveFileType.defaultExtension
    override fun createCommandLine(): GeneralCommandLine = GeneralCommandLine("move-analyzer")

}

//private object PrismaLspServerPackageDescriptor : LspServerPackageDescriptor("@prisma/language-server",
//    PackageVersion.downloadable("6.3.1"),
//    "/dist/bin.js") {
//    private val sinceNewServiceLayoutVersion = SemVer.parseFromText("5.15.0")!! // inclusive
//
//    override val registryVersion: String get() = Registry.stringValue("prisma.language.server.default.version")
//
//    override fun getPackageRelativePath(project: Project, ref: NodePackageRef): String {
//        val version = ref.constantPackage?.version
//        if (version != null && !version.isGreaterOrEqualThan(sinceNewServiceLayoutVersion)) {
//            return "/dist/src/bin.js"
//        }
//
//        return super.getPackageRelativePath(project, ref)
//    }
//}
//
//class PrismaLspServerSupportProvider : LspServerSupportProvider {
//    override fun fileOpened(project: Project, file: VirtualFile, serverStarter: LspServerSupportProvider.LspServerStarter) {
//        if (PrismaLspServerActivationRule.isLspServerEnabledAndAvailable(project, file)) {
//            serverStarter.ensureServerStarted(PrismaLspServerDescriptor(project))
//        }
//    }
//
//    override fun createLspServerWidgetItem(lspServer: LspServer, currentFile: VirtualFile?): LspServerWidgetItem =
//        LspServerWidgetItem(lspServer, currentFile, PrismaIcons.PRISMA, settingsPageClass = PrismaSettingsConfigurable::class.java)
//}
//
//fun restartPrismaServerAsync(project: Project) {
//    ApplicationManager.getApplication().invokeLater(Runnable {
//        LspServerManager.getInstance(project).stopAndRestartIfNeeded(PrismaLspServerSupportProvider::class.java)
//    }, project.disposed)
//}
//
//object PrismaLspServerLoader : LspServerLoader(PrismaLspServerPackageDescriptor) {
//    override fun getSelectedPackageRef(project: Project): NodePackageRef =
//        PrismaServiceSettings.getInstance(project).lspServerPackageRef
//
//    override fun restartService(project: Project) {
//        restartPrismaServerAsync(project)
//    }
//}