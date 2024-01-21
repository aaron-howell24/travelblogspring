package com.travelblog.travelblogspring.controller.Api;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.travelblog.travelblogspring.Exceptions.ObjectExistsException;
import com.travelblog.travelblogspring.model.User;
import com.travelblog.travelblogspring.model.dto.UserDto;
import com.travelblog.travelblogspring.repository.UserRepository;

@Controller
@RequestMapping("/api/user")
public class ApiUserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/create")
	public ResponseEntity<Object> create(
			@RequestBody UserDto userDto
			) {
		
		Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
		
		if(existingUser.isPresent()) {
	        throw new ObjectExistsException(
	                "There is an account with that email adress:" + userDto.getEmail());
		}
		
		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setIsActive(userDto.getIsActive() != null ? userDto.getIsActive() : true );
		user.setProfilePicSrc(userDto.getProfilePicSrc());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromPath("/api/user/{id}")
				.buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User get(@PathVariable long id) throws NotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);

		if (optionalUser.isEmpty()) {
			throw new NotFoundException();
		}
		User user = optionalUser.get();
//		user.setRoles(null);

		return user;
	}
//	@GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	@Transactional
//	public User getDetails(@PathVariable long id) throws NotFoundException {
//		Optional<User> optionalUser = userRepository.findById(id);
//		
//		if (optionalUser.isEmpty()) {
//			throw new NotFoundException();
//		}
//		
//		User user = optionalUser.get();
//		
////        Hibernate.initialize(user.getRoles());
//
//		return user;
//	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(
			@RequestBody UserDto userDto,
			@PathVariable long id){
		Optional<User> userOptional = userRepository.findById(id);

		if (userOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Boolean updated = false;
		
		User managedUser = userOptional.get();
		
		if(userDto.getEmail() != managedUser.getEmail()) {
			Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
			
			if(existingUser.isPresent()) {
				throw new ObjectExistsException(
						"There is an account with that email adress:" + userDto.getEmail());
			}
			
			managedUser.setEmail(userDto.getEmail());
			updated = true;
		}
		
		if(userDto.getPassword() != null) {
			managedUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
			updated = true;
		}
		
		if(userDto.getFirstName() != managedUser.getFirstName()) {
			managedUser.setFirstName(userDto.getFirstName());
			updated = true;
		}
		if(userDto.getLastName() != managedUser.getLastName()) {
			managedUser.setLastName(userDto.getLastName());
			updated = true;
		}
		
		if(userDto.getProfilePicSrc() != managedUser.getProfilePicSrc()) {
			managedUser.setProfilePicSrc(userDto.getProfilePicSrc());
			updated = true;
		}
		
		if(userDto.getIsActive() != managedUser.getIsActive()) {
			managedUser.setIsActive(userDto.getIsActive());
			updated = true;
		}
			
		if(updated) {			
			userRepository.save(managedUser);
		}
		
		return ResponseEntity.noContent().build();

	}
	

}
