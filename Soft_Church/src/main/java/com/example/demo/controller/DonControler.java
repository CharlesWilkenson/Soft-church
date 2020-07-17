package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.Espece;
import com.example.demo.entities.Nature;
import com.example.demo.entities.TraceSysteme;
import com.example.demo.services.Services;

@Controller
@RequestMapping(value = "/soft_church")
public class DonControler {
	@Autowired
	private Services dao;
	
	@RequestMapping(value = "/form_don")
	public String formDon(Model model,Nature nature,Espece espece) {
		model.addAttribute("nature", nature);
		model.addAttribute("espece", espece);
		return "enregistrer_don";
	}
	
	
	
	@RequestMapping(value = "/save_don_nature")
	public String save_don_nature(Model model,Nature nature) {
		try {
			dao.enregistrerDon_nature(nature);
			
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				
				dao.addTrace(new TraceSysteme(username, "Save Don", new Date(), new Date()));
					}
			
			model.addAttribute("nature", nature);
			model.addAttribute("espece", new Espece());
			model.addAttribute("success", "success");
		}catch(Exception e) {
			model.addAttribute("faillure", "faillure");
		}
		
		return "enregistrer_don";
	}
	
	@RequestMapping(value = "/save_don_espece")
	public String save_don_espece(Model model,Espece espece) {
		try {
			dao.enregistrerDon_espece(espece);
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				
				dao.addTrace(new TraceSysteme(username, "Save Don", new Date(), new Date()));
					}
			
			model.addAttribute("nature", new Nature());
			model.addAttribute("espece", espece);
			model.addAttribute("success", "success");
		}catch(Exception e) {
			model.addAttribute("faillure", "faillure");
		}
		
		return "enregistrer_don";
	}
	
	@RequestMapping(value = "/lister_don_especes")
	public String lister_don_especes(Model model) {
		List<Espece>list=dao.listerEspeces();
		model.addAttribute("don_especes",list);
		
		return "lister_don_especes";
	}
	
	@RequestMapping(value = "/lister_don_natures")
	public String lister_don_natures(Model model) {
		List<Nature>list=dao.listerNatures();
		model.addAttribute("don_natures",list);
		 
		return "lister_don_natures";
	}
	
}
