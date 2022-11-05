package com.timsmithsoftware.integration_tests;

import java.util.concurrent.CompletableFuture;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.timsmithsoftware.integration_tests.models.Connection;
import com.timsmithsoftware.integration_tests.tests.GetVisitsTests;

public class SystemConfiguration {
	Connection[] connections;
	
	public CompletableFuture<Boolean> waitForConnections() {
		return CompletableFuture.completedFuture(false);
		}
	
	public void runTests() {
		JUnitCore junit = new JUnitCore();
		Result result = junit.run(GetVisitsTests.class);
	}

}

