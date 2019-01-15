package com.datangliang.app.web.rest.vm.mock;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class MockUser {
	
	public String nickname;
	
	public Boolean isRealNameAuth=true;
	
	public Boolean isSiteAuth=true;
	
	public Boolean isEnterpriseAuth=true;
	
	public Integer userType=1;
	
	public String businessTime = "8:00-19:00";
	
	public String businessInfo="专注白砂糖30年";
	
	
	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("nickname", "mock 昵称");
		json.put("isRealNameAuth", isRealNameAuth);
		json.put("isSiteAuth", isSiteAuth);
		json.put("isEnterpriseAuth", isEnterpriseAuth);
		json.put("userType", userType);
		json.put("businessTime", businessTime);
		json.put("businessInfo", businessInfo);
		return json;
	}

}
