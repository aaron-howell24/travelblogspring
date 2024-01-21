//package com.travelblog.travelblogspring.model;
//
//import com.travelblog.travelblogspring.enums.RoleType;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import jakarta.persistence.UniqueConstraint;
//
//@Entity
//@Table(uniqueConstraints={
//    @UniqueConstraint(columnNames = {"user_id", "role_id"})
//})
//public class UserRole {
//	
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    
//    @ManyToOne
//    @JoinColumn(name="user_id",nullable=false)
//	private User user;
//    
//    @Enumerated(EnumType.STRING)
//    private RoleType role;
//	
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
//	public RoleType getRole() {
//		return role;
//	}
//	public void setRole(RoleType role) {
//		this.role = role;
//	}
//	public Long getId() {
//		return id;
//	}
//	
//}
