package com.timsmithsoftware.integration_tests.models

interface Connection {
    fun waitUntilAlive(): Boolean
}