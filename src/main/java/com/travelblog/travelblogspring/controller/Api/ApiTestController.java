package com.travelblog.travelblogspring.controller.Api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ApiTestController {
	
	@GetMapping("/test")
	public ResponseEntity<String> test(){
		return ResponseEntity.ok("Test Successful");
	}
}
