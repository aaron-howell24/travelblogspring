package com.travelblog.travelblogspring.controller.Blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model) {
		return "blog/home";
	}

	@GetMapping("/home")
	public String redirectHome(Model model) {
		return "redirect:/";
	}

}
