package com.timsmithsoftware.integration_tests.tests

import com.timsmithsoftware.integration_tests.Constants
import com.timsmithsoftware.integration_tests.TestBuilder
import com.timsmithsoftware.integration_tests.models.ApiRequest
import com.timsmithsoftware.integration_tests.models.ApiResponse
import com.timsmithsoftware.integration_tests.models.TestResult
import com.timsmithsoftware.integration_tests.models.connections.ApiConnection
import com.timsmithsoftware.integration_tests.models.connections.DatabaseConnection
import org.json.JSONObject
import org.junit.Assert
import org.junit.Test
import java.net.HttpURLConnection
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers

class PostVisitTests {
    @Test
    fun postVisitHappyPath() {
        try {
            val json = "{" +
                    "\"lastName\":\"Smith\"," +
                    "\"firstName\":\"John\"" +
                    "}"
            val httpRequest = HttpRequest
                    .newBuilder(URI(Constants.POST_VISITS_URL))
                    .POST(BodyPublishers.ofString(json))
                    .build()
            val request = ApiRequest(httpRequest)

            val response = ApiResponse(
                    HttpURLConnection.HTTP_CREATED,
                    JSONObject(json)
            )
            val expectedTestResult = TestResult.TRUE

            val httpClient = HttpClient.newHttpClient()
            val apiConnection = ApiConnection(httpClient)
            val databaseConnection = DatabaseConnection()

            val test = TestBuilder(apiConnection, databaseConnection)
            val result = test
                    .build()
                    .withRequest(request)
                    .withExpectedResponse(response) //.withExpectedDatabaseChange(DatabaseChange.noChange) // change expected, tbd
                    .call()
                    .toResult()
            Assert.assertEquals(result, expectedTestResult)
        } catch (e: Exception) {
            Assert.fail(e.message)
        }
    }

    @Test
    fun postVisitReturnsUnprocessableEntityIfNotEnoughInformationProvided() {
    }
}