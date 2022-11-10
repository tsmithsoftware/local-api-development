package com.timsmithsoftware.integration_tests.models

import com.timsmithsoftware.integration_tests.models.database.User
import com.timsmithsoftware.integration_tests.models.database.Users

interface Connection {
    fun waitUntilAlive(): Boolean
}

interface IDatabaseConnection: Connection {
    fun getUsers(): List<User>
}
interface IApiConnection: Connection {
    fun call(request: IApiRequest): ApiResponse
}