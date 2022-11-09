package com.timsmithsoftware.integration_tests.tests

import com.timsmithsoftware.integration_tests.Constants
import com.timsmithsoftware.integration_tests.SystemConfiguration
import com.timsmithsoftware.integration_tests.TestBuilder
import com.timsmithsoftware.integration_tests.models.ApiRequest
import com.timsmithsoftware.integration_tests.models.ApiResponse
import com.timsmithsoftware.integration_tests.models.DatabaseChange
import com.timsmithsoftware.integration_tests.models.TestResult
import com.timsmithsoftware.integration_tests.models.ApiConnection
import com.timsmithsoftware.integration_tests.models.DatabaseConnection
import org.json.JSONObject
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import java.net.HttpURLConnection
import java.net.URI
import java.net.URISyntaxException
import java.net.http.HttpClient
import java.net.http.HttpRequest

/**
 * Integration test for GetVisits end point.
 */
class GetVisitsTests {

    companion object {

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            println("GetVisits - waiting for connection")
            val config = SystemConfiguration()
            config.waitForConnections()
        }
    }

    @org.junit.jupiter.api.Test
    fun visitsReturnsCorrectVisitsFromDatabase() {
        try {
            val httpRequest = HttpRequest
                    .newBuilder(URI(Constants.VISITS_URL))
                    .GET()
                    .build()
            val request = ApiRequest(httpRequest)

            val jsonString = """{
            'users': [
            {
                'lastName': 'Doe',
                'firstName': 'John'
            },
            {
                'lastName': 'Smith',
                'firstName': 'Danny'
            },
            {
                'lastName': 'Smith',
                'firstName': 'Harry'
            }
            ]
        }""";

            val response = ApiResponse(HttpURLConnection.HTTP_OK, JSONObject(jsonString))

            val httpClient = HttpClient.newHttpClient()
            val apiConnection = ApiConnection(httpClient)

            val databaseConnection = DatabaseConnection()

            val test = TestBuilder(apiConnection, databaseConnection)
            val result = test
                    .build()
                    .withRequest(request)
                    .withExpectedResponse(response)
                    .withExpectedDatabaseChange(DatabaseChange.NO_CHANGE)
                    .call()
                    .toResult()

            Assertions.assertEquals(TestResult.TRUE, result)
        } catch (e: URISyntaxException) {
            Assertions.fail()
        }
    }
}