package com.openmind.springjwt.domain;

import com.fasterxml.jackson.annotation.JsonProperty;


public class User {
    
	@JsonProperty("user-id")
	private int id;
	
	@JsonProperty("user-name")
	private String userName;
	
	@JsonProperty("user-password")
	private String password;
	
	@JsonProperty("user-authorities")
	private String authorities;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	
	
}
