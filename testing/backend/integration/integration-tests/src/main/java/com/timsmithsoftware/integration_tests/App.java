package com.timsmithsoftware.integration_tests;

public class App 
{
    public static void main( String[] args )
    {
        // run system tests - check we can talk to the DB and API
        SystemConfiguration config = new SystemConfiguration();
        config
        .waitForConnections()
        .thenApply(
        		(result) -> {
        			return result;
        			});
        // run integration tests
        config.runTests();
    }
}
