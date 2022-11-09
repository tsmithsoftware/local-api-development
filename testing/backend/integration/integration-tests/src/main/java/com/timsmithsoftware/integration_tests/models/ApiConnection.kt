package com.timsmithsoftware.integration_tests.models

import com.timsmithsoftware.integration_tests.Constants
import com.timsmithsoftware.integration_tests.models.ApiRequest
import com.timsmithsoftware.integration_tests.models.ApiResponse
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Service
class ApiConnection (private val _client: HttpClient): Connection {

    fun call(request: ApiRequest): ApiResponse {
        val response: HttpResponse<String> = _client.send(request.request, HttpResponse.BodyHandlers.ofString())
        return ApiResponse(response.statusCode(), JSONObject(response.body().toString()))
    }

    override fun waitUntilAlive(): Boolean {

        try {
            val httpRequest = HttpRequest
                .newBuilder(URI(Constants.BASE_URL))
                .GET()
                .build()
            val response = _client.send(httpRequest, HttpResponse.BodyHandlers.ofString())
            while (response.statusCode() != HttpURLConnection.HTTP_NO_CONTENT) {
                Thread.sleep(200)
                return waitUntilAlive()
            }
        } catch (e: ConnectException) {
            Thread.sleep(200)
            return waitUntilAlive()
        }
        return true
    }
}