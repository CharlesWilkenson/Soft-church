package com.example.demo.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping(value="/soft_church")
public class securiteControler {

	@RequestMapping(value = "/login")
	public String login() {
			
		return "login";
	}
	
	
	@RequestMapping(value = "/")
	public String home() {
			
		return "redirect:/formsaveMembre";
	}
}
