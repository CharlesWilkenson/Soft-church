package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.Culte;
import com.example.demo.entities.TraceSysteme;
import com.example.demo.services.Services;

@Controller
@RequestMapping(value = "/soft_church")
public class TraceController {
	@Autowired
	private Services dao;
	
	
	
	@RequestMapping(value = "/lister_traces")
	public String lister_traces(Model model,Culte culte) {
	 try {
             List<TraceSysteme>lst=dao.listerTrace();
           
	    	 model.addAttribute("traces",lst);
	    	 
	 }catch(Exception e) {
		 
	             }  
	    
	    	return "lister_trace";
	    }
}
