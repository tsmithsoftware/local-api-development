package com.timsmithsoftware.integration_tests.models

import com.timsmithsoftware.integration_tests.SystemConfiguration
import org.json.JSONObject
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.net.HttpURLConnection
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class ApiConnectionTests {

    private val mockClient: HttpClient = mock(HttpClient::class.java)

    private val mockHttpRequest: HttpRequest = mock(HttpRequest::class.java)

    private val apiConnection = ApiConnection(mockClient)

    private val mockRequest = ApiRequest(mockHttpRequest)

    private fun setUpMockSuccess() {
        val mockSuccess: HttpResponse<String> = mock(HttpResponse::class.java) as HttpResponse<String>

        `when`(mockSuccess.body()).thenReturn("{'success':true}")
        `when`(mockSuccess.statusCode()).thenReturn(200)

        `when`(mockClient.send(
                mockHttpRequest, HttpResponse.BodyHandlers.ofString()
        )).thenReturn(mockSuccess)
    }

    private fun setUpMockFailure() {
        val mockFailure: HttpResponse<String> = mock(HttpResponse::class.java) as HttpResponse<String>

        `when`(mockFailure.body()).thenReturn("{'success':false}")
        `when`(mockFailure.statusCode()).thenReturn(400)

        `when`(mockClient.send(
                mockHttpRequest, HttpResponse.BodyHandlers.ofString()
        )).thenReturn(mockFailure)
    }

    @org.junit.jupiter.api.Test
    fun shouldAnswerWithTrueIfApiConnectionSucceeds() {

        setUpMockSuccess()

        val result = apiConnection.call(mockRequest)

        val expectedResponse = ApiResponse(200, JSONObject("{'success':true}"))

        verify(mockClient).send(
                mockHttpRequest, HttpResponse.BodyHandlers.ofString()
        )

        assertEquals(expectedResponse, result)
    }

    @org.junit.jupiter.api.Test
    fun shouldAnswerWithFalseIfApiConnectionFails() {
        setUpMockFailure()
        val mockRequest = ApiRequest(mockHttpRequest)

        val result = apiConnection.call(mockRequest)

        val expectedResponse = ApiResponse(HttpURLConnection.HTTP_BAD_REQUEST, JSONObject("{'success':false}"))

        verify(mockClient).send(
                mockHttpRequest, HttpResponse.BodyHandlers.ofString()
        )

        assertEquals(expectedResponse, result)
    }

    @Test
    fun checkAliveShouldReturnTrueIfAvailable() {
        val mockSuccess: HttpResponse<String> = mock(HttpResponse::class.java) as HttpResponse<String>
        `when`(mockSuccess.statusCode()).thenReturn(HttpURLConnection.HTTP_NO_CONTENT)

        `when`(mockClient.send<String>(
                any(), any()
        )).thenReturn(mockSuccess)

        val result = apiConnection.waitUntilAlive()

        verify(mockClient).send<String>(
                any(), any()
        )

        assertTrue(result)
    }
}