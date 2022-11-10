package com.timsmithsoftware.integration_tests.models

import java.net.http.HttpRequest

interface IApiRequest {
    val request: HttpRequest?
}

class ApiRequest(override var request: HttpRequest): IApiRequest