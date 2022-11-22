package com.timsmithsoftware.integration_tests.models

class ApiResponse(private val code: Int, private val body: Any?) {
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

    override fun hashCode(): Int {
        var result = code
        result = 31 * result + (body?.hashCode() ?: 0)
        return result
    }
}