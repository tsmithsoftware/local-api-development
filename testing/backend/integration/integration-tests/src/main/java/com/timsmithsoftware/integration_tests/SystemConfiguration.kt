package com.timsmithsoftware.integration_tests

import com.timsmithsoftware.integration_tests.models.ApiConnection
import com.timsmithsoftware.integration_tests.models.Connection
import com.timsmithsoftware.integration_tests.models.DatabaseConnection
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
}