package com.example.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.Cash;
import com.example.demo.entities.Cheque;
import com.example.demo.entities.Dime;
import com.example.demo.entities.TraceSysteme;
import com.example.demo.services.Services;

@Controller
@RequestMapping(value = "/soft_church")
public class DimeControler {
@Autowired
private Services dao;
	
	@RequestMapping(value = "/formSaveDime")
	public String formSaveDime(Model model,Cheque cheque,Cash cash) {
		
	model.addAttribute("chequedime", cheque);	
	model.addAttribute("cash", cash);		
	return "enregistrer_dime";	
	}
	
	@RequestMapping(value = "/saveDimeCash")
	public String saveDime(Model model,Cash cash) {
    try {
		dao.enregistrerDimeCash(cash);
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			dao.addTrace(new TraceSysteme(username, "Save dime", new Date(), new Date()));
			
			
		}
		
		model.addAttribute("cheque", new Cheque());
		model.addAttribute("succes", "succes");
model.addAttribute("cash", cash);
		}catch(Exception e) {
			model.addAttribute("faillure", "faillure");
		model.addAttribute("dime", new Cheque());
		model.addAttribute("cash", cash);
		}
	return "enregistrer_dime";	
	}
	
	
	@RequestMapping(value = "/saveDimeCheque")
	public String saveDimeCheque(Model model,Cheque cheque) {

	
	try {

		dao.enregistrerDimeCheque(cheque);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			dao.addTrace(new TraceSysteme(username, "Save dime", new Date(), new Date()));
			
			
		}
		
        model.addAttribute("cheque", cheque);
        model.addAttribute("cash", new Cash());
        model.addAttribute("succes", "succes");
		}catch(Exception e) {
			model.addAttribute("faillure", "faillure");
			model.addAttribute("cheque", cheque);
			model.addAttribute("cash", new Cash());
		}
	return "enregistrer_dime";	
	}
	
	
	
	@RequestMapping(value = "/listerDimesCashes")
	public String listerDimesCashes(Model model) {
		
		
	model.addAttribute("cashes", dao.listerCashes());	
		
	return "lister_dime_cashes";	
	}
	
	
	
	@RequestMapping(value = "/listerDimesCheques")
	public String listerDimesCheques(Model model) {
		
		
	model.addAttribute("cheques", dao.listerCheques());	
		
	return "lister_dimes_cheques";	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
