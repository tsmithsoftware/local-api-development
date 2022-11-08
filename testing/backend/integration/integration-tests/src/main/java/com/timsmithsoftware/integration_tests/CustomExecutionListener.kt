package com.timsmithsoftware.integration_tests

import org.junit.runner.Description
import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunListener

class CustomExecutionListener: RunListener() {
    override fun testFinished(description: Description) {
        //Write the test name when it is finished.
        println("""${description.methodName} test is finished...""")
        super.testFinished(description)
    }

    override fun testFailure(failure: Failure) {
        //Write the test name when it is failed.
        println(failure.description.methodName + " test FAILED!!!")
        super.testFailure(failure)
    }

}