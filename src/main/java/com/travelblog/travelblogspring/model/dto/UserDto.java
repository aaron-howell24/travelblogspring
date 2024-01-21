package com.travelblog.travelblogspring.model.dto;

public class UserDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Boolean isActive;
	private String profilePicSrc;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getProfilePicSrc() {
		return profilePicSrc;
	}
	public void setProfilePicSrc(String profilePicSrc) {
		this.profilePicSrc = profilePicSrc;
	}
	

}
