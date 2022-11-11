package com.timsmithsoftware.integration_tests.models

class TestResult (var resultNotes: String = ""){
    companion object {
        private lateinit var failureReason: String
        fun FALSE(reason: String): TestResult {
            this.failureReason = reason
            return TestResult(resultNotes = reason)
        }

        val TRUE: TestResult = TestResult()
    }

}