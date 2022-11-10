package com.timsmithsoftware.integration_tests.models

class DatabaseConnection : IDatabaseConnection {

    override fun execute(statement: String) {  }

    override fun waitUntilAlive(): Boolean {
        // TODO Auto-generated method stub
        return true
    }
}