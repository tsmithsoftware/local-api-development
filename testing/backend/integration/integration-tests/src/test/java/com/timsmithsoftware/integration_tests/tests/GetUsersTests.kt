package com.timsmithsoftware.integration_tests.tests

import com.timsmithsoftware.integration_tests.Constants
import com.timsmithsoftware.integration_tests.SystemConfiguration
import com.timsmithsoftware.integration_tests.TestBuilder
import com.timsmithsoftware.integration_tests.models.*
import com.timsmithsoftware.integration_tests.models.database.User
import org.json.JSONObject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import java.net.HttpURLConnection
import java.net.URI
import java.net.URISyntaxException
import java.net.http.HttpClient
import java.net.http.HttpRequest
import org.junit.jupiter.api.Test

/**
 * Integration test for GetVisits end point.
 */
class GetUsersTests {

    companion object {

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            println("GetUsers - waiting for connection")
            val config = SystemConfiguration()
            config.waitForConnections()
        }
    }

    @Test
    fun visitsReturnsCorrectVisitsFromDatabase() {
        try {
            val httpRequest = HttpRequest
                    .newBuilder(URI(Constants.GET_USERS_API_URL))
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
        }"""

            val expectedApiResponse = ApiResponse(HttpURLConnection.HTTP_OK, JSONObject(jsonString))

            val httpClient = HttpClient.newHttpClient()
            val apiConnection = ApiConnection(httpClient)

            val databaseConnection = DatabaseConnection()

            // TODO set up DI
            val test = TestBuilder(apiConnection, databaseConnection)
            val testRun = test
                    .build()
                    .withDescription("GetVisits returns correct visit list from database")
                    .withRequest(request)
                    .withExpectedResponse(expectedApiResponse)
                    .withExpectedDatabaseChange { dbConnection: DatabaseConnection -> confirmChange(dbConnection) }
                    .call()

            val testResult = testRun.toResult()

            Assertions.assertEquals(TestResult.TRUE, testResult, testResult.resultNotes)
        } catch (e: URISyntaxException) {
            Assertions.fail()
        }
    }

    private fun confirmChange(dbConnection: DatabaseConnection): Boolean {
        val users = dbConnection.getUsers()
        val john: User? = users.find { it.lastName == "Doe" && it.firstName == "John"}
        val danny: User? = users.find { it.lastName == "Smith" && it.firstName == "Danny"}
        val harry: User? = users.find { it.lastName == "Smith" && it.firstName == "Harry"}
        return john != null && danny != null && harry != null
    }
}