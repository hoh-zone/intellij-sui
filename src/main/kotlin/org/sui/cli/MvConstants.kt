package org.sui.cli

object MvProjectLayout {
    val SOURCES_DIRS = arrayOf("sources", "examples", "scripts")
    const val TESTS_DIR = "tests"
    const val BUILD_DIR = "build"
}

object MvConstants {
    const val MANIFEST_FILE = "Move.toml"
    const val ADDR_PLACEHOLDER = "_"
}
