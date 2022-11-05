package com.timsmithsoftware.integration_tests.models;

public class TestResult {
	public TestResult(boolean result) {
		this.result = result;
	}

	public boolean result;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }
        
        final TestResult other = (TestResult) obj;
        return other.result == this.result;
    }
}
