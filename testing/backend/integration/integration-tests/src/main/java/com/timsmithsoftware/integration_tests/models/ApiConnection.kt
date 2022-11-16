package com.timsmithsoftware.integration_tests.models

import com.timsmithsoftware.integration_tests.Constants
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Service
class ApiConnection (private val _client: HttpClient): IApiConnection {

    override fun call(request: IApiRequest): ApiResponse {
        val response: HttpResponse<String> = _client.send(request.request, HttpResponse.BodyHandlers.ofString())
        var body: JSONObject? = null
        response.body()?.let {
            if(it.isNotEmpty()) {
                body = JSONObject(it)
            }
        }
        return ApiResponse(response.statusCode(), body)
    }

    override fun waitUntilAlive(): Boolean {

        try {
            val httpRequest = HttpRequest
                .newBuilder(URI(Constants.BASE_API_URL))
                .GET()
                .build()
            println("ApiConnection.waitUntilAlive, making call")
            val response = _client.send(httpRequest, HttpResponse.BodyHandlers.ofString())
            println("status code: ${response.statusCode()}, body: ${response.body()}")
            while (response.statusCode() != HttpURLConnection.HTTP_NO_CONTENT) {
                println("sleeping...")
                Thread.sleep(200)
                return waitUntilAlive()
            }
        } catch (e: ConnectException) {
            Thread.sleep(200)
            return waitUntilAlive()
        }
        println("alive!")
        return true
    }
}