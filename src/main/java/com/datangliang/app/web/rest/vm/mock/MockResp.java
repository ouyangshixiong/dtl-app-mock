/**
 * 
 */
package com.datangliang.app.web.rest.vm.mock;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 * @author Administrator
 *
 */
public class MockResp {
	
	public String version="1.0";
	
	public Integer code=0;
	
	public String message;
	
	public MockResp() {
		// TODO Auto-generated constructor stub
	}
	
	public MockResp(String message) {
		this.message = message;
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("version", version);
		json.put("message", message);
		return json;
	}


}
