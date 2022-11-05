package com.timsmithsoftware.integration_tests.models;

import java.net.http.HttpRequest;

public class ApiRequest {
	public ApiRequest(HttpRequest httpRequest) {
		this.request = httpRequest;
	}

	public HttpRequest request;
}
