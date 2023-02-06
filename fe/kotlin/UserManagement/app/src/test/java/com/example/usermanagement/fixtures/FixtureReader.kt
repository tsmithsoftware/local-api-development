package com.example.usermanagement.fixtures

import java.io.File
import java.nio.file.Paths

val readFromFile = { path: String -> File("${Paths.get("src/test/java/com/example/usermanagement/fixtures/").toAbsolutePath()}/$path").readText() }