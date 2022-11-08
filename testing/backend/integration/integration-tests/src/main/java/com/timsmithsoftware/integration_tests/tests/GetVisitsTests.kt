package com.timsmithsoftware.integration_tests.tests

import com.timsmithsoftware.integration_tests.Constants
import com.timsmithsoftware.integration_tests.TestBuilder
import com.timsmithsoftware.integration_tests.models.ApiRequest
import com.timsmithsoftware.integration_tests.models.ApiResponse
import com.timsmithsoftware.integration_tests.models.DatabaseChange
import com.timsmithsoftware.integration_tests.models.TestResult
import com.timsmithsoftware.integration_tests.models.connections.ApiConnection
import com.timsmithsoftware.integration_tests.models.connections.DatabaseConnection
import org.json.JSONObject
import org.junit.Assert
import org.junit.Test
import java.net.HttpURLConnection
import java.net.URI
import java.net.URISyntaxException
import java.net.http.HttpClient
import java.net.http.HttpRequest

/**
 * Integration test for GetVisits end point.
 */
class GetVisitsTests {

    @Test
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
                'userId': 1,
                'lastName': 'Doe',
                'firstName': 'John',
                'createdAt': '2022-11-08T11:17:58.000Z',
                'updatedAt': '2022-11-08T11:17:58.000Z'
            },
            {
                'userId': 2,
                'lastName': 'Smith',
                'firstName': 'Danny',
                'createdAt': '2022-11-08T11:17:58.000Z',
                'updatedAt': '2022-11-08T11:17:58.000Z'
            },
            {
                'userId': 3,
                'lastName': 'Smith',
                'firstName': 'Harry',
                'createdAt': '2022-11-08T11:17:58.000Z',
                'updatedAt': '2022-11-08T11:17:58.000Z'
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

            Assert.assertEquals(result, TestResult.TRUE)
        } catch (e: URISyntaxException) {
            Assert.fail()
        }
    }
}