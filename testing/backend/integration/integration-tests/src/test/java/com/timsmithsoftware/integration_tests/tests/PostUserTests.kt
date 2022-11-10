package com.timsmithsoftware.integration_tests.tests

import com.timsmithsoftware.integration_tests.Constants
import com.timsmithsoftware.integration_tests.SystemConfiguration
import com.timsmithsoftware.integration_tests.TestBuilder
import com.timsmithsoftware.integration_tests.models.ApiRequest
import com.timsmithsoftware.integration_tests.models.ApiResponse
import com.timsmithsoftware.integration_tests.models.TestResult
import com.timsmithsoftware.integration_tests.models.ApiConnection
import com.timsmithsoftware.integration_tests.models.DatabaseConnection
import com.timsmithsoftware.integration_tests.models.database.User
import org.json.JSONObject
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import java.net.HttpURLConnection
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers
import org.junit.jupiter.api.Test

class PostUserTests {

    companion object {

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            println("PostUser - waiting for connection")
            val config = SystemConfiguration()
            config.waitForConnections()
        }

        @AfterEach
        @JvmStatic
        internal fun afterEach(){
            println("PostUser - cleaning up data")
            DatabaseConnection().removeUser("Smith", "John")
        }
    }

    @Test
    fun postVisitHappyPath() {
        try {
            val json = "{" +
                    "\"lastName\":\"Smith\"," +
                    "\"firstName\":\"John\"" +
                    "}"

            val httpRequest = HttpRequest
                    .newBuilder(URI(Constants.POST_USERS_URL))
                    .POST(BodyPublishers.ofString(json))
                    .build()

            val request = ApiRequest(httpRequest)

            val expectedResponse = ApiResponse(
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
                    .withExpectedResponse(expectedResponse)
                //.withExpectedDatabaseChange(DatabaseChange.noChange) // change expected, tbd
                    .call()
                    .toResult()
            
            Assertions.assertEquals(result, expectedTestResult)
        } catch (e: Exception) {
            Assertions.fail(e.message)
        }
    }

    @Test
    fun postVisitReturnsUnprocessableEntityIfNotEnoughInformationProvided() {
    }
}