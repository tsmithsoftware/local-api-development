package com.timsmithsoftware.integration_tests.models

import org.json.JSONObject

class ApiResponse(private val code: Int, private val body: JSONObject?) {
    override fun equals(other: Any?): Boolean {
        if (other != null) {
            if (other.javaClass == this.javaClass) {
                val otherResponse = other as ApiResponse
                val otherBody = otherResponse.body.toString()
                val thisBody = body.toString()
                val stringBodyEquality = otherBody == thisBody
                if (this.code == otherResponse.code && stringBodyEquality) {
                    return true
                }
            }
        }
        return false
    }

    override fun toString(): String {
        return "Code: $code, Body: $body"
    }
}