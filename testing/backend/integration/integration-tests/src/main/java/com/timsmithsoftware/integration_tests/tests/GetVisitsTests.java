package com.timsmithsoftware.integration_tests.tests;

import org.junit.Test;
import com.timsmithsoftware.integration_tests.Constants;
import com.timsmithsoftware.integration_tests.TestBuilder;
import com.timsmithsoftware.integration_tests.models.ApiRequest;
import com.timsmithsoftware.integration_tests.models.ApiResponse;
import com.timsmithsoftware.integration_tests.models.DatabaseChange;
import com.timsmithsoftware.integration_tests.models.TestResult;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import org.json.JSONObject;

import static org.junit.Assert.*;

/**
 * Integration test for GetVisits end point.
 */
public class GetVisitsTests 
{
    @Test
    public void getVisitsReturnsCorrectVisitsFromDatabase()
    {
		try {
			HttpRequest httpRequest = HttpRequest
			.newBuilder(new URI(Constants.VISITS_URL))
			.GET()
			.build();
			
    	ApiRequest request = new ApiRequest(httpRequest);
    	
    	String json = "{'users':[{'userId':1,'lastName':'Doe','firstName':'John','createdAt':'2022-09-10T15:50:28.000Z','updatedAt':'2022-09-10T15:50:28.000Z'},{'userId':2,'lastName':'Smith','firstName':'Danny','createdAt':'2022-09-10T15:50:28.000Z','updatedAt':'2022-09-10T15:50:28.000Z'},{'userId':3,'lastName':'Smith','firstName':'Harry','createdAt':'2022-09-10T15:50:28.000Z','updatedAt':'2022-09-10T15:50:28.000Z'},{'userId':4,'lastName':'gates','firstName':'bill','createdAt':'2022-09-10T15:51:33.000Z','updatedAt':'2022-09-10T15:51:33.000Z'},{'userId':5,'lastName':'gates','firstName':'bill','createdAt':'2022-09-10T15:53:14.000Z','updatedAt':'2022-09-10T15:53:14.000Z'},{'userId':6,'lastName':'gates','firstName':'bill','createdAt':'2022-09-10T15:53:28.000Z','updatedAt':'2022-09-10T15:53:28.000Z'}]}";
    	ApiResponse response = new ApiResponse(HttpURLConnection.HTTP_OK, new JSONObject(json));
		
    	TestResult expectedTestResult = new TestResult(true);
    	
    	TestBuilder test = new TestBuilder();
    	TestResult result = test
    	.build()
    	.withRequest(request)
    	.withExpectedResponse(response)
    	.withExpectedDatabaseChange(DatabaseChange.noChange)
    	.call()
    	.toResult();
    	
    	assertEquals(result, expectedTestResult);
		} catch (URISyntaxException e) {
 			fail();
 		}
    }
}
