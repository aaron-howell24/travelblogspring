//package com.travelblog.travelblogspring.controller.Api;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.travelblog.travelblogspring.model.User;
//import com.travelblog.travelblogspring.model.UserRole;
//import com.travelblog.travelblogspring.model.dto.UserRoleDto;
//import com.travelblog.travelblogspring.repository.UserRepository;
//import com.travelblog.travelblogspring.repository.UserRoleRepository;
//
//@RestController
//@RequestMapping("/api/user_role")
//public class ApiUserRoleController {
//	
//	@Autowired
//	UserRepository userRepository;
//	
//	@Autowired
//	UserRoleRepository userRoleRepository;
//	
//	@PostMapping("/add")
//	public ResponseEntity<Object> addRoleToUser(
//			@RequestBody UserRoleDto userRoleDto){
//		
//		System.out.println("1st: UserRoleDto Role: " + userRoleDto.getRole().name());
//
//		
//		Optional<User> userOptional = userRepository.findById(userRoleDto.getUserId());
//		
//		if (userOptional.isEmpty()) {
//			return ResponseEntity.status(400).body("User not found");
//		}
//		
//		Optional<UserRole> optionalUserRole = userRoleRepository.findByUserIdAndRole(userRoleDto.getUserId(), userRoleDto.getRole());
//		
//		if(optionalUserRole.isPresent()) {
//			return ResponseEntity.status(409).body("User already has that role");
//		}
//		
//		System.out.println("UserRoleDto Role: " + userRoleDto.getRole().name());
//		
//		UserRole userRole = new UserRole();
//		userRole.setUser(userOptional.get());
//		userRole.setRole(userRoleDto.getRole());
//		
//		userRoleRepository.save(userRole);
//		
//		return ResponseEntity.ok().build();
//	}
//}
