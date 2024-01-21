package com.travelblog.travelblogspring.controller.Admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {
	
	
	@GetMapping({"","home"})
	public String adminHome(Model model) {
		DateFormat dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.DEFAULT);
		
		model.addAttribute("serverTime", dateFormat.format(new Date()));
		
		return "admin/home";
		
	}

}
