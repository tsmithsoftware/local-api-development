package com.timsmithsoftware.integration_tests.tests

import com.timsmithsoftware.integration_tests.Constants
import com.timsmithsoftware.integration_tests.SystemConfiguration
import com.timsmithsoftware.integration_tests.TestBuilder
import com.timsmithsoftware.integration_tests.models.*
import com.timsmithsoftware.integration_tests.models.database.User
import org.json.JSONObject
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.net.HttpURLConnection
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers

class PostUserTests {

    companion object {

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            println("PostUser - waiting for connection")
            val config = SystemConfiguration()
            config.waitForConnections()
        }

        @AfterAll
        @JvmStatic
        internal fun afterAll() {
            println("AfterAll - cleaning up data")
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
                    .newBuilder(URI(Constants.POST_USER_API_URL))
                    .POST(BodyPublishers.ofString(json))
                    .build()

            val request = ApiRequest(httpRequest)

            val expectedResponse = ApiResponse(
                    HttpURLConnection.HTTP_CREATED,
                    JSONObject(json)
            )

            // TODO set up DI
            val httpClient = HttpClient.newHttpClient()
            val apiConnection = ApiConnection(httpClient)
            val databaseConnection = DatabaseConnection()

            val test = TestBuilder(apiConnection, databaseConnection)
            val result = test
                    .build()
                    .withRequest(request)
                    .withExpectedResponse(expectedResponse)
                    .withExpectedDatabaseChange { dbConnection: DatabaseConnection -> confirmChange(dbConnection) }
                    .call()
                    .toResult()
            
            Assertions.assertEquals(TestResult.TRUE, result, result.resultNotes)
        } catch (e: Exception) {
            Assertions.fail(e.message)
        }
    }

    private fun confirmChange(dbConnection: DatabaseConnection): Boolean{
        val users = dbConnection.getUsers()
        val johnSmith: User? = users.find { it.lastName == "Smith" && it.firstName == "John"}
        return johnSmith != null
    }

    @Test
    fun postVisitReturnsUnprocessableEntityIfNotEnoughInformationProvided() {
    }
}