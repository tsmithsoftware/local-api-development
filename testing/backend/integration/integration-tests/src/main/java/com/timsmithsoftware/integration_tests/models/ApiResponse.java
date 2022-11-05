package com.timsmithsoftware.integration_tests.models;
import org.json.JSONObject;

public class ApiResponse {
	private int code;
	private JSONObject body;
	
	public ApiResponse(int code, JSONObject body) {
		this.code = code;
		this.body = body;
	}
	

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj.getClass() == this.getClass()) {
            	final ApiResponse other = (ApiResponse) obj;
            	String otherBody = other.body.toString();
            	String thisBody = this.body.toString();
            	Boolean stringBodyEquality = otherBody.equals(thisBody);
            	if(this.code == other.code && stringBodyEquality) {
            		return true;
            	}
            }
        }
        return false;
    }
}
