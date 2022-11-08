package com.timsmithsoftware.integration_tests

import com.timsmithsoftware.integration_tests.models.connections.ApiConnection
import com.timsmithsoftware.integration_tests.models.connections.Connection
import com.timsmithsoftware.integration_tests.models.connections.DatabaseConnection
import com.timsmithsoftware.integration_tests.tests.GetVisitsTests
import com.timsmithsoftware.integration_tests.tests.PostVisitTests
import org.junit.runner.JUnitCore
import org.junit.runner.Result
import java.net.http.HttpClient

class SystemConfiguration {
    private val httpClient: HttpClient = HttpClient.newHttpClient()
    private val connections: List<Connection> = listOf(
            DatabaseConnection(),
            ApiConnection(httpClient)
    )

    fun waitForConnections(): Boolean {
        for(connection in connections) {
            connection.waitUntilAlive()
        }
        return true
    }

    fun runTests(): List<Result> {
        val junit = JUnitCore()
        junit.addListener(CustomExecutionListener())
        val getVisitsResult =  junit.run(GetVisitsTests::class.java)
        val postVisitsResult = junit.run(PostVisitTests::class.java)
        return listOf(
                getVisitsResult,
                postVisitsResult
        )
    }
}