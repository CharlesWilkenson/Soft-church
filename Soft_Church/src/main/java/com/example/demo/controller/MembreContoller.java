package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;

import com.example.demo.entities.*;
import com.example.demo.services.Services;

import org.apache.commons.io.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@Controller
@RequestMapping(value = "/soft_church")
public class MembreContoller {
	@Autowired
	private Services dao;


	HttpSession session;
	String username;
	String id;
	@RequestMapping(value = "/home")
	public String home(Membre membre, HttpSession session) {
	
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
			dao.addTrace(new TraceSysteme(username, "Connexion", new Date(), new Date()));
			
			
			
			  List<Membre> list=dao.listerMembre(username);
			  for(Membre m:list) { membre.setCode(m.getCode()); membre.setNom(m.getNom());
			  membre.setPrenom(m.getPrenom()); 
		
				
			
				  
			  session.setAttribute("code",membre.getCode()); 
			  session.setAttribute("nom", membre.getNom());
			  session.setAttribute("prenom", membre.getPrenom());
			  session.setAttribute("photo",membre.getPhoto());
			  id=(String) session.getAttribute("code");
			  } 

		} else {
			String username = principal.toString();
			System.out.println("Code 2" + username);
		}

		return "home";
	}

	
	
	
	
	@RequestMapping(value = "/formsaveMembre")
	public String formSaveMembre(Model model, Membre membre) {

		model.addAttribute("membre", membre);
		return "enregistrer_membre";
	}

	@RequestMapping(value = "/formsearchMembre")
	public String formsearchMembre(Model model, Membre membre) {

		model.addAttribute("membre", membre);
		return "rechercher_membre";
	}

	@RequestMapping(value = "/formlisterMembre")
	public String formlisterMembre(Model model, Membre membre) {
		List<Membre> list = dao.listerMembre();
		model.addAttribute("membres", list);
		model.addAttribute("membre", membre);
		return "lister_membre";
	}

	@RequestMapping(value = "/updateMembre",
			method = RequestMethod.POST)
	public String updateMembre(Membre membre, Model model,
	@RequestParam(name = "picture") MultipartFile picture) {
String email=null;
              if (!picture.isEmpty()) {

			try {
				
				membre.setPhoto(picture.getBytes());
				model.addAttribute("membre", membre);

				dao.updateMembre(membre);
				Object principal = SecurityContextHolder
						.getContext()
						.getAuthentication()
						.getPrincipal();
				if (principal instanceof UserDetails) {
					 email=((UserDetails) principal).getUsername();
				}
				dao.addTrace(new TraceSysteme(email, "Modifier Membre", new Date()));
				model.addAttribute("succes", "succes");
			   } catch (Exception e) {
				model.addAttribute("faillure", "faillure");
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			}

		}else {
			
			try {
				
				model.addAttribute("membre", membre);
               byte[] photo=getPhoto(membre.getCode());
               membre.setPhoto(photo);
				dao.updateMembre(membre);
				Object principal = SecurityContextHolder
						.getContext()
						.getAuthentication()
						.getPrincipal();
				if (principal instanceof UserDetails) {
					 email=((UserDetails) principal).getUsername();
				}
				dao.addTrace(new TraceSysteme(email, "Modifier Membre", new Date()));
				model.addAttribute("succes", "succes");
			} catch (Exception e) {
				model.addAttribute("faillure", "faillure");
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			}
			
		}

		return "rechercher_membre";

	}

	@RequestMapping(value = "/saveMembre", method = RequestMethod.POST)
	public String savemembre(Membre membre, Model model,
			@RequestParam(name = "picture") MultipartFile picture) {
		List<Membre>listm=dao.listerMembre(membre.getEmail());
		String email = null,cin_nif=null,tel=null;
		
		
		if(listm.size()>0) {
			for(Membre m:listm) {
			email=m.getEmail();
			cin_nif=m.getIdentification();
			tel=m.getTel();
				
			}
			
			if(email.equals(membre.getEmail()))
	      model.addAttribute("errorEmail", "Cet email existe deja")	;
			if(tel.equals(membre.getTel()))
			      model.addAttribute("errorTel", "Ce nume de telephone existe deja")	;
		if(cin_nif.equals(membre.getIdentification()))
		model.addAttribute("errorId", "Ce numero d'identification  existe deja")	;
		
		}else
		
	
		if (!picture.isEmpty()) {
	
			try {
				
				membre.setPhoto(picture.getBytes());
				model.addAttribute("membre", membre);

				dao.saveMembre(membre);
				Object principal = SecurityContextHolder
						.getContext()
						.getAuthentication()
						.getPrincipal();
				if (principal instanceof UserDetails) {
					 email=((UserDetails) principal).getUsername();
				}
				dao.addTrace(new TraceSysteme(email, "Enregistrer Membre", new Date(), new Date()));
				
				model.addAttribute("succes", "succes");
				model.addAttribute("code", membre.getCode());
			} catch (Exception e) {
				model.addAttribute("faillure", "faillure");
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			}

		}

		return formSaveMembre(model, membre);

	}

	@RequestMapping(value = "/rechercherMembre")
	public String rechercherMembre(Model model, String code) {

		try {
			String email = null;
			Membre membre = dao.findMembre(code);
		
			
			 if (membre == null) { 
				 model.addAttribute("membre", new Membre()); 
			     model.addAttribute("exception", "code invalide");
			 
			 } else {
			 
					
					Object principal = SecurityContextHolder
							.getContext()
							.getAuthentication()
							.getPrincipal();
					if (principal instanceof UserDetails) {
						 email=((UserDetails) principal).getUsername();
					}
					dao.addTrace(new TraceSysteme(email, "Rechercher Membre", new Date(), new Date()));
					
				model.addAttribute("membre", membre);
			 }
		} catch (Exception e) {
			model.addAttribute("exception", e);
		

		}
		return "rechercher_membre";
	}

	@RequestMapping(value = "/getPhoto", 
			produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(String code) throws IOException {
		System.out.println("Method getPhoto is called 1");
		ByteArrayInputStream b = null;
		byte[] io = null;
		System.out.println("Code "+code);
		try {
            Membre m=dao.findMembre(code);
         
			byte[] photo=m.getPhoto();
			b = new ByteArrayInputStream(photo);
		
			io = IOUtils.toByteArray(b);
		} catch (Exception e) {

		
		}

		return io;
	}

	@RequestMapping(value = "/editerMembre", method = RequestMethod.GET)
	public String editerMembre(Model model, String codeMembre) {

		try {
			Membre mb = dao.findMembre(codeMembre);
			if (mb == null) {
				model.addAttribute("membre", new Membre());
			} else
				model.addAttribute("membre", mb);
		} catch (Exception e) {

		}
		return "lister_membre";
	}

	@RequestMapping(value = "/profileMembre")
	public String profileMembre(Model model, Membre membre) {
		String email=null;
		try {
		membre = dao.findMembre(id);
		if(membre==null) {
			model.addAttribute("membre",new Membre());	
			
			
		}else
		model.addAttribute("code", membre.getCode());
		model.addAttribute("prenom", membre.getPrenom());
		model.addAttribute("nom", membre.getNom());
		model.addAttribute("sexe", membre.getSexe());
		model.addAttribute("adresse", membre.getAdresse());
		model.addAttribute("dateAjout", membre.getDateAjout());
		model.addAttribute("statut", membre.getStatutMatrimonial());
		model.addAttribute("id", membre.getIdentification());
		model.addAttribute("dateNaiss", membre.getDateNaissance());
		model.addAttribute("lieuNaiss", membre.getLieuNaissance());
		model.addAttribute("nationalite", membre.getNationalite());
		model.addAttribute("tel", membre.getTel());
		model.addAttribute("email", membre.getEmail());
		model.addAttribute("membre", membre);
		Object principal = SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		if (principal instanceof UserDetails) {
			 email=((UserDetails) principal).getUsername();
		}
		dao.addTrace(new TraceSysteme(email, "Voir profile", new Date()));
		
		
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "profile_membre";
	}

	@RequestMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImage() throws IOException {
		ByteArrayInputStream b = null;
		byte[] io = null;
		try {

			byte[] photo = dao.findMembre(id).getPhoto();
			b = new ByteArrayInputStream(photo);

			io = IOUtils.toByteArray(b);
		} catch (Exception e) {

		
		}

		return io;
	}


	@RequestMapping(value ="/getMembre")
	public  String getMembre(Model model,
			String code) {
		
		try {
		System.out.println("Test getMembre");
	      	 Membre m =dao.findMembre(code);
	      	 if(m==null) {
	      		 model.addAttribute("membre", new Membre());
	      	 }else
	
		

			 model.addAttribute("membre", m);
		}catch(Exception e) {
			
		}
		return "modifier_membre";
	}
	
	
	
	@RequestMapping(value = "/modifierMembre",
			method = RequestMethod.POST)
	public String modifierMembre(Membre membre, Model model,
	@RequestParam(name = "picture") MultipartFile picture) {
String email=null;
              if (!picture.isEmpty()) {

			try {
				
				membre.setPhoto(picture.getBytes());
				model.addAttribute("membre", membre);

				dao.updateMembre(membre);
				Object principal = SecurityContextHolder
						.getContext()
						.getAuthentication()
						.getPrincipal();
				if (principal instanceof UserDetails) {
					 email=((UserDetails) principal).getUsername();
				}
				dao.addTrace(new TraceSysteme(email, "Modifier Membre", new Date(), new Date()));
				
				model.addAttribute("succes", "succes");
			   } catch (Exception e) {
				model.addAttribute("faillure", "faillure");
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			}

		}else {
			
			try {
			
				model.addAttribute("membre", membre);
               byte[] photo=getPhoto(membre.getCode());
               membre.setPhoto(photo);
				dao.updateMembre(membre);
				Object principal = SecurityContextHolder
						.getContext()
						.getAuthentication()
						.getPrincipal();
				if (principal instanceof UserDetails) {
					 email=((UserDetails) principal).getUsername();
				}
				dao.addTrace(new TraceSysteme(email, "Modifier Membre", new Date(), new Date()));
				
				model.addAttribute("succes", "succes");
			} catch (Exception e) {
				model.addAttribute("faillure", "faillure");
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			}
			
		}

		return "modifier_membre";

	}
}

