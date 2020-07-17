package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entities.Culte;
import com.example.demo.entities.Membre;
import com.example.demo.entities.TraceSysteme;
import com.example.demo.services.Services;

@Controller
@RequestMapping(value = "/soft_church")
public class CulteController {
	@Autowired
	private Services dao;
	String username;
	private Map<String, Membre> map=new HashMap<String, Membre>();
	
	
		@RequestMapping(value = "/formSaveCulte")
		public String formSaveDime(Model model,Culte culte) {
			
		model.addAttribute("culte", culte);			
		return "Enregistrer_culte";	
		}
		
	
		public Map<String, Membre> getMap() {
			return map;
		}


		public void setMap(Map<String, Membre> map) {
			this.map = map;
		}


		@RequestMapping(value = "/ajouterMembre")
		public String Ajoutermembre (Model model,String code) {
		
			try {
			Membre mb=dao.findMembre(code);
			if(mb==null) {
				  model.addAttribute("exception", "code invalide");
				  model.addAttribute("culte", new Culte());
			}
			
			map.put(mb.getCode(), mb);
		if(!(mb==null) && map.size()>=3) {
			
			 model.addAttribute("show", "show");	
		}
	
		  model.addAttribute("culte", new Culte());
		
			}catch(Exception e) {
				
				 model.addAttribute("culte", new Culte());
			}
			
			membres( model);
            return "Enregistrer_culte";
		}



		//Lister les lignes d'achat
		@RequestMapping(value = "/lstmembres")
		public  String membres(Model model){
			Collection<Map.Entry<String, Membre>> values = map.entrySet();
	        //Creating an ArrayList of values
			System.out.println("Method is called");
			model.addAttribute("membres", new ArrayList<Map.Entry<String,Membre>>(values));
			return "Enregistrer_culte";
		}
	

		@RequestMapping(value = "/retirerMembre")
		public String retirerMembre(String code,Model model) {
			 map.remove(code);
			 membres( model);
			 model.addAttribute("culte", new Culte());
		return "Enregistrer_culte";	
		}
	
		
		@RequestMapping(value = "/saveCulte", method = RequestMethod.POST)
		public String saveCulte(Model model,Culte culte) {
		 try {
			 
		    	//System.out.println("Method is called ");
		    	//System.out.println("Fin "+fin);
		    	dao.enregistrerCulte(culte, map);
		    	
		    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof UserDetails) {
					username = ((UserDetails) principal).getUsername();
					
					dao.addTrace(new TraceSysteme(username, "Save culte", new Date(), new Date()));
						}
		    	
		    	 model.addAttribute("culte", new Culte());
		    	 map.clear();
		 }catch(Exception e) {
			 
		             }  
		    
		    	return "Enregistrer_culte";
		    }
		
		
		@RequestMapping(value = "/lister_cultes")
		public String lister_cultes(Model model,Culte culte) {
		 try {
	             List<Culte>lst=dao.listerCultes();
	           
		    	 model.addAttribute("cultes",lst);
		    	 
//	 System.out.println("Size"+c.getMembre().size());
		 }catch(Exception e) {
			 
		             }  
		    
		    	return "lister_cultes";
		    }	
		

		@RequestMapping(value = "/presence_au_culte")
	//@ResponseBody
		public String presence_au_culte(Model model,@PathParam("id")Long id) {
			  System.out.println("ID "+id);
			
			Culte c=dao.getCulte(id);
					 for(Membre mb: c.getMembres()) { 
						 System.out.println("Nom "+mb.getNom());
					  
					  System.out.println("Prenom "+mb.getPrenom());
					  
					  }
					  System.out.println("ID 2 "+id);
					 model.addAttribute("presences",c.getMembres());
					 model.addAttribute("quantite","Il y avait "+c.getMembres().size()+" membres presents");
						
					 return "lister_presences"; 
		}
		
		
		
		/*
		 * @RequestMapping(value = "/presence_au_culte") public String
		 * presence_au_culte(Model model,Long id) { try { Culte c=dao.getCulte((long)
		 * 18); for(Membre mb: c.getMembres()) { System.out.println("Nom "+mb.getNom());
		 * 
		 * System.out.println("Prenom "+mb.getPrenom());
		 * 
		 * }
		 * 
		 * model.addAttribute("presences",c.getMembres());
		 * 
		 * }catch(Exception e) {
		 * 
		 * }
		 * 
		 * return "lister_cultes"; }
		 */	
			
}
