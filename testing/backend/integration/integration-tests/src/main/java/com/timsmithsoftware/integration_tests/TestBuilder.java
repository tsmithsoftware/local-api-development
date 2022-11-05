package com.timsmithsoftware.integration_tests;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONObject;
import com.timsmithsoftware.integration_tests.models.ApiConnection;
import com.timsmithsoftware.integration_tests.models.ApiRequest;
import com.timsmithsoftware.integration_tests.models.ApiResponse;
import com.timsmithsoftware.integration_tests.models.DatabaseChange;
import com.timsmithsoftware.integration_tests.models.DatabaseConnection;
import com.timsmithsoftware.integration_tests.models.TestResult;

public class TestBuilder {
	private ApiConnection apiConnection = new ApiConnection();
	private DatabaseConnection databaseConnection = new DatabaseConnection();
	
	private ApiRequest _request;
	private ApiResponse _expectedResponse;
	private ApiResponse _actualResponse;
	private DatabaseChange _databaseChange;
	
	public TestBuilder withRequest(ApiRequest request) {
		this._request = request;
		return this;
	}
	
	public TestBuilder withExpectedResponse(ApiResponse response) {
		this._expectedResponse = response;
		return this;
	}

	public TestBuilder withExpectedDatabaseChange(DatabaseChange change) {
		this._databaseChange = change;
		return this;
	}

	public TestBuilder call() {
		// make API request
		// create a client
		HttpClient client = HttpClient.newHttpClient();

		// use the client to send the request
		HttpResponse response;
		try {
			response = client.send(_request.request, BodyHandlers.ofString());
			// the response:
			System.out.println(response.body());

			// check API response
			_actualResponse = new ApiResponse(response.statusCode(), new JSONObject(response.body().toString()));
			// check DB change has been done correctly
			return this;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}

	public TestResult toResult() {
		if (_expectedResponse != null && _actualResponse != null) {
			return new TestResult(_expectedResponse.equals(_actualResponse));
		}
		// check DBResults
		return new TestResult(false);
	}

	public TestBuilder build() {
		return this;
	}
}
