package com.timsmithsoftware.integration_tests

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val config = SystemConfiguration()
        // run system tests - check we can talk to the DB and API
        config.waitForConnections()
        // run API integration tests
        val results = config.runTests()
        // handle results
    }
}