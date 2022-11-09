package com.timsmithsoftware.integration_tests

import com.timsmithsoftware.integration_tests.models.ApiRequest
import com.timsmithsoftware.integration_tests.models.ApiResponse
import com.timsmithsoftware.integration_tests.models.DatabaseChange
import com.timsmithsoftware.integration_tests.models.TestResult
import com.timsmithsoftware.integration_tests.models.ApiConnection
import com.timsmithsoftware.integration_tests.models.DatabaseConnection
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.IOException
import java.net.http.HttpClient
import java.net.http.HttpResponse
import java.net.http.HttpResponse.BodyHandlers

class TestBuilder(private var apiConnection: ApiConnection, var databaseConnection: DatabaseConnection) {

    private var _request: ApiRequest? = null
    private var _expectedResponse: ApiResponse? = null
    private var _actualResponse: ApiResponse? = null
    private var _databaseChange: DatabaseChange? = null

    fun withRequest(request: ApiRequest?): TestBuilder {
        _request = request
        return this
    }

    fun withExpectedResponse(response: ApiResponse?): TestBuilder {
        _expectedResponse = response
        return this
    }

    fun withExpectedDatabaseChange(change: DatabaseChange?): TestBuilder {
        _databaseChange = change
        return this
    }

    fun call(): TestBuilder {
            _request?.let {
                _actualResponse = apiConnection.call(it)
                // check DB change has been done correctly
            }
            return this
    }

    fun toResult(): TestResult {
        _expectedResponse?.let { expected ->
            _actualResponse?.let { actual ->
                val passes = expected == actual
                if (passes) {
                    return TestResult.TRUE
                } else {
                    return TestResult.FALSE
                }
                // check DBResults
            }
        }
        return TestResult.FALSE
    }

    fun build(): TestBuilder {
        return this
    }
}