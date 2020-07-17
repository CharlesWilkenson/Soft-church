package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.Services;

@Controller
@RequestMapping(value = "/soft_church")
public class AdminController {
	@Autowired
	private Services dao;
	

	@RequestMapping(value = "/dashboard")
	public String dashboardAdmin(Model model) {
		model.addAttribute("membres", dao.listerMembre().size());
		model.addAttribute("cultes", dao.listerCultes().size());
		model.addAttribute("dimesCashes", dao.listerCashes().size());
		model.addAttribute("dimesCheques", dao.listerCheques().size());
		model.addAttribute("don_natures", dao.listerNatures().size());
		model.addAttribute("don_especes", dao.listerEspeces().size());
		model.addAttribute("offrandes", dao.listerOffrandes().size());
		return"dashboard";
	}
}
