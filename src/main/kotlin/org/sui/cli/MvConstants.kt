package org.sui.cli

import com.intellij.openapi.externalSystem.model.ProjectSystemId
import org.sui.stdext.exists
import java.nio.file.Path

object MvProjectLayout {
    val SOURCES_DIRS = arrayOf("sources", "examples", "scripts")
    const val TESTS_DIR = "tests"
    const val BUILD_DIR = "build"

    fun dirs(root: Path): List<Path> {
        val names = listOf(*SOURCES_DIRS, TESTS_DIR, BUILD_DIR)
        return names.map { root.resolve(it) }.filter { it.exists() }
    }
}

object MvConstants {
    const val MANIFEST_FILE = "Move.toml"
    const val ADDR_PLACEHOLDER = "_"
    const val MOVE_COMPILER_V2_ENV = "MOVE_LANGUAGE_V2"

    const val PSI_FACTORY_DUMMY_FILE = "DUMMY_PSI_FACTORY.move"

    val PROJECT_SYSTEM_ID = ProjectSystemId("Sui Move")
}
