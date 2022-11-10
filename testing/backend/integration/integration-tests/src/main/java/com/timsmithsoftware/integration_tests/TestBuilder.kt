package com.timsmithsoftware.integration_tests

import com.timsmithsoftware.integration_tests.models.*

class TestBuilder(private var apiConnection: IApiConnection, var databaseConnection: Connection) {

    private var _request: IApiRequest? = null
    private var _expectedResponse: ApiResponse? = null
    private var _actualResponse: ApiResponse? = null
    private var _databaseChange: ((DatabaseConnection) -> Boolean)? = null

    fun withRequest(request: IApiRequest?): TestBuilder {
        _request = request
        return this
    }

    fun withExpectedResponse(response: ApiResponse?): TestBuilder {
        _expectedResponse = response
        return this
    }

    fun withExpectedDatabaseChange(change: ((DatabaseConnection) -> Boolean)): TestBuilder {
        _databaseChange = change
        return this
    }

    fun call(): TestBuilder {
            _request?.let {
                _actualResponse = apiConnection.call(it)
            }
            return this
    }

    fun toResult(): TestResult {
        _databaseChange?.let { _databaseChange ->
            _expectedResponse?.let { expected ->
                _actualResponse?.let { actual ->
                    val passes = expected == actual
                    if (passes) {
                        val dbConn = databaseConnection
                        if(dbConn is DatabaseConnection) {
                            val testPasses = _databaseChange.invoke(dbConn)
                            testPasses.let {
                                if(it) {
                                    return TestResult.TRUE
                                } else {
                                    return TestResult.FALSE
                                }
                            }
                        }
                    } else {
                        return TestResult.FALSE
                    }
                    // check DBResults
                }
            }
        }
        return TestResult.FALSE
    }

    fun build(): TestBuilder {
        return this
    }
}