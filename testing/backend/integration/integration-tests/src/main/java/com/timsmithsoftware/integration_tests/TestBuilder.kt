package com.timsmithsoftware.integration_tests

import com.timsmithsoftware.integration_tests.models.*

class TestBuilder(private var apiConnection: IApiConnection, var databaseConnection: Connection) {

    private var _request: IApiRequest? = null
    private var _expectedResponse: ApiResponse? = null
    private var _actualResponse: ApiResponse? = null
    private var _databaseChange: ((DatabaseConnection) -> Boolean)? = null
    private var _testDescription: String? = null
    private var _testResult: TestResult? = null

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

    fun withNoExpectedDatabaseChange(): TestBuilder {
        _databaseChange = { _: DatabaseConnection -> true }
        return this
    }

    fun call(): TestBuilder {
            _request?.let {
                _actualResponse = apiConnection.call(it)
            }
            return this
    }

    fun toResult(): TestResult {
        // TODO move some validation to the call() function
        _databaseChange?.let { _databaseChange ->
            _expectedResponse?.let { expected ->
                _actualResponse?.let { actual ->
                    val passes = expected == actual
                    if (passes) {
                        val dbConn = databaseConnection
                        if(dbConn is DatabaseConnection) {
                            val testPasses = _databaseChange.invoke(dbConn)
                            testPasses.let {
                                if (it) {
                                    _testResult = TestResult.TRUE
                                    return _testResult!!
                                } else {
                                    _testResult = TestResult.FALSE("Test failed due to incorrect DB change")
                                    return _testResult!!
                                }
                            }
                        }
                    } else {
                        _testResult = TestResult.FALSE(
                            "Test failed due to incorrect API response\n" +
                                    "Expected response:" +
                                    "$expected" +
                                    "Actual response: " +
                                    "$actual"
                        )
                        return _testResult!!
                    }
                }
                _testResult = TestResult.FALSE("Test failed due to no API response")
                return _testResult!!
            }
            _testResult = TestResult.FALSE("Please add the expected API change before calling the test")
            return _testResult!!
        }
        _testResult = TestResult.FALSE("Please add the expected database change before calling the test")
        return _testResult!!
    }

    fun build(): TestBuilder {
        return this
    }

    fun withDescription(description: String): TestBuilder {
        _testDescription = description
        return this
    }
}