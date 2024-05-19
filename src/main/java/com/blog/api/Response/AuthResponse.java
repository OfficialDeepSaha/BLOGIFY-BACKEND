package com.blog.api.Response;

import com.blog.api.Dto.UserDTO;

public class AuthResponse {

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	private String jwt;

	private boolean status;

	private String message;

	private UserDTO  user;

	public AuthResponse() {

	}

	public AuthResponse(String jwt, boolean status, String message, UserDTO user) {
		super();
		this.jwt = jwt;
		this.status = status;
		this.message = message;
		this.user = user;
	}

	
	
	

}
