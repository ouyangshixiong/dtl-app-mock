/**
 * 
 */
package com.datangliang.app.web.rest.vm.mock;

/**
 * @author Administrator
 *
 */
public class MockRegister {
	
	private String phone;
	
	private String password;
	
	private String code;
	
	private Integer source; //1：大糖粮网，2：聚一搜获，3、大糖粮APP
	
	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}
	
	
	

}
