package com.timsmithsoftware.integration_tests.tests

import com.timsmithsoftware.integration_tests.Constants
import com.timsmithsoftware.integration_tests.SystemConfiguration
import com.timsmithsoftware.integration_tests.TestBuilder
import com.timsmithsoftware.integration_tests.models.ApiRequest
import com.timsmithsoftware.integration_tests.models.ApiResponse
import com.timsmithsoftware.integration_tests.models.TestResult
import com.timsmithsoftware.integration_tests.models.ApiConnection
import com.timsmithsoftware.integration_tests.models.DatabaseConnection
import org.json.JSONObject
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import java.net.HttpURLConnection
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers

class PostVisitTests {

    companion object {

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            println("PostVisits - waiting for connection")
            val config = SystemConfiguration()
            config.waitForConnections()
        }
    }

    @org.junit.jupiter.api.Test
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
            Assertions.assertEquals(result, expectedTestResult)
        } catch (e: Exception) {
            Assertions.fail(e.message)
        }
    }

    @org.junit.jupiter.api.Test
    fun postVisitReturnsUnprocessableEntityIfNotEnoughInformationProvided() {
    }
}