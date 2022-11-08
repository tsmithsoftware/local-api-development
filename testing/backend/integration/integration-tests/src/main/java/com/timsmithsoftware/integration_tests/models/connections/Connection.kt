package com.timsmithsoftware.integration_tests.models.connections

import java.util.concurrent.Future

interface Connection {
    fun waitUntilAlive(): Boolean
}