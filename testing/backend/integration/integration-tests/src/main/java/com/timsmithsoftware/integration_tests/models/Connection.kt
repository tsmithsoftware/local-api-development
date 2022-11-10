package com.timsmithsoftware.integration_tests.models

interface Connection {
    fun waitUntilAlive(): Boolean
}

interface IDatabaseConnection: Connection {
    fun execute(statement: String)
}
interface IApiConnection: Connection {
    fun call(request: IApiRequest): ApiResponse
}