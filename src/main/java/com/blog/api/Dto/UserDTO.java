package com.blog.api.Dto;

import jakarta.persistence.Column;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UserDTO {

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", email=" + email + ", message=" + message + ", password="
				+ password + ", role=" + role + ", about=" + about + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	private Long id;
	
	private String name;
	
	@Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@Column(nullable = false, unique = true)
	@NotEmpty(message = "Email is not empty!")
	private String email;
	
	private String message;
	
	@Column(nullable = false)
	private String password;
	
	private String role;
	
	private String about;

	public UserDTO() {
		
	}

	public UserDTO(Long id, String name,
			@Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") @NotEmpty(message = "Email is not empty!") String email,
			String message, String password, String role, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.message = message;
		this.password = password;
		this.role = role;
		this.about = about;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
