package com.timsmithsoftware.integration_tests.tests;

import org.json.JSONObject;
import org.junit.Test;

import com.timsmithsoftware.integration_tests.Constants;
import com.timsmithsoftware.integration_tests.TestBuilder;
import com.timsmithsoftware.integration_tests.models.ApiRequest;
import com.timsmithsoftware.integration_tests.models.ApiResponse;
import com.timsmithsoftware.integration_tests.models.DatabaseChange;
import com.timsmithsoftware.integration_tests.models.TestResult;

import static org.junit.Assert.*;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;

public class PostVisitTests {
    @Test
    public void postVisitHappyPath()
    {
    	try {
    		 String json = "{" +
    	                "\"lastName\":\"Smith\"," +
    	                "\"firstName\":\"John\"" +
    	                "}";
    		 
			HttpRequest httpRequest = HttpRequest
			.newBuilder(new URI(Constants.POST_VISITS_URL))
			.POST(BodyPublishers.ofString(json))
			.build();
			
			ApiRequest request = new ApiRequest(httpRequest);
			ApiResponse response = new ApiResponse(
					HttpURLConnection.HTTP_CREATED, 
					new JSONObject(json)
					);
			
	    	TestResult expectedTestResult = new TestResult(true);
	    	
	    	TestBuilder test = new TestBuilder();
	    	TestResult result = test
	    	.build()
	    	.withRequest(request)
	    	.withExpectedResponse(response)
	    	//.withExpectedDatabaseChange(DatabaseChange.noChange) // change expected, tbd
	    	.call()
	    	.toResult();
	    	
	    	assertEquals(result, expectedTestResult);
    	} catch(Exception e) {
    		fail(e.getMessage());
    	} 
    }
    
    @Test
    public void postVisitReturnsUnprocessableEntityIfNotEnoughInformationProvided()
    {}
}
