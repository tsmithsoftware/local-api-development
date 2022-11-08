package com.timsmithsoftware.integration_tests.models.connections

import com.timsmithsoftware.integration_tests.Constants
import com.timsmithsoftware.integration_tests.models.ApiRequest
import com.timsmithsoftware.integration_tests.models.ApiResponse
import org.json.JSONObject
import org.springframework.stereotype.Service
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
        val httpRequest = HttpRequest
                .newBuilder(URI(Constants.BASE_URL))
                .GET()
                .build()
        var response = _client.send(httpRequest, HttpResponse.BodyHandlers.ofString())
        while (response.statusCode() != 200) {
            response?.body()?.let {
                if(!response.body().toString().contains("existingRoutes")){
                    Thread.sleep(200)
                    response = _client.send(httpRequest, HttpResponse.BodyHandlers.ofString())
                }
                return true
            }
            Thread.sleep(200)
            response = _client.send(httpRequest, HttpResponse.BodyHandlers.ofString())
        }
        return true
    }
}