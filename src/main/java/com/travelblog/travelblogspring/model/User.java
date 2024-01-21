package com.travelblog.travelblogspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String firstName;

	@Column(nullable=false)
	private String lastName;
	
	@Column(nullable=false)
	private String email;
	
	@JsonIgnore
	@Column(nullable=false)
	private String password;
	
	@Column(name="is_active",nullable=false,columnDefinition="boolean default true")
	private Boolean isActive;
	
	private String profilePicSrc;
	
//    @OneToMany(mappedBy="user")
//    private Set<UserRole> roles;
	
	public long getId() {
		return id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public Set<UserRole> getRoles() {
//		return roles;
//	}
//	public void setRoles(Set<UserRole> roles) {
//		this.roles = roles;
//	}	
	
	
}
