package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entities.Role;
import com.example.demo.entities.TraceSysteme;
import com.example.demo.entities.Utilisateur;
import com.example.demo.services.Services;

import org.springframework.security.core.Authentication;

@Controller
@RequestMapping(value = "/soft_church")
public class UserController {
	@Autowired
	private Services dao;
	@RequestMapping(value="/logout")
	public String logoutPage (String id,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Method called ");
		
		
    String email = null;
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	System.out.println("1 "+auth.getCredentials());
	System.out.println("2 "+auth.getPrincipal());	
	
	Object principal = SecurityContextHolder
			.getContext()
			.getAuthentication()
			.getPrincipal();
	dao.addTrace(new TraceSysteme(((UserDetails) principal).getUsername(), "Deconnexion", new Date(), new Date()));			
	   
	
	if (auth != null) {
			
			
			if (principal instanceof UserDetails) {
				 email=((UserDetails) principal).getUsername();
			}
			 new SecurityContextLogoutHandler().logout(request, response, auth);
		
		}
	    return "login?logout";
	}
	

	
	@RequestMapping(value = "/formChangePassword")
	public String formSaveMembre(Model model, Utilisateur u) {

		model.addAttribute("user", u);
		return "changePassword";
	}
	
	

	
	
	@RequestMapping(value = "/changePassword")
	public String changePassword(Model model,Utilisateur u,
			HttpServletRequest request, HttpServletResponse response) {
		String email = null;	
		System.out.println("change Password");
		try {
			Object principal = SecurityContextHolder
					.getContext()
					.getAuthentication()
					.getPrincipal();
			if (principal instanceof UserDetails) {
				 email=((UserDetails) principal).getUsername();
			}
	
			    u.setEmail(email);
				dao.changePassword(u);
			
				dao.addTrace(new TraceSysteme(email, "Change Password", new Date(), new Date()));
				
				org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				//if (auth != null) {
				    new SecurityContextLogoutHandler().logout(request, response, auth);
				//}
				model.addAttribute("succes", "succes");
		//	}
		
			model.addAttribute("user", new Utilisateur());
			
		}catch (Exception e) {
			model.addAttribute("faillure", "faillure");
			System.out.println("Error change password "+e.getMessage());
		}
		
		return "redirect:/soft_church/login?logout";
	}
	
	@RequestMapping(value = "/formAttribuerRole")
	public String formAttribuerRole(Model model,Utilisateur u) {

		model.addAttribute("user",u);
		return "attribuerRole";
	}
	
	
	
	@RequestMapping(value = "/attribuerRole")
	public String formAttribuerRole(Model model, Utilisateur u,String role) {
        String email=null;       
		try {
			  u=dao.findUser(u.getEmail()); 
		
			  if(u==null) {
				  model.addAttribute("errorRole", "Cet utilisateur n'existe pas");
				  model.addAttribute("user", new Utilisateur());			
			
			  }else{
	
					    dao.attribuerRole(u, role);
					    Object principal = SecurityContextHolder
								.getContext()
								.getAuthentication()
								.getPrincipal();
						if (principal instanceof UserDetails) {
							 email=((UserDetails) principal).getUsername();
						}
						dao.addTrace(new TraceSysteme(email, "Attribuer Role", new Date(), new Date()));
						model.addAttribute("user", new Utilisateur());
						model.addAttribute("succes","succes");			            
			  }
			 		
		
  }  catch(Exception e) {
	  model.addAttribute("user", new Utilisateur());
      model.addAttribute("faillure", "faillure");
		  System.out.println("Error "+e.getMessage());
            }
		return "attribuerRole";
	}


	@RequestMapping(value = "/listerUsers")
public String listerusers(Model model){
	List<Utilisateur>listerUser=dao.listerUser();
	
	model.addAttribute("users", listerUser);
	
return"lister_user";	
}

	@RequestMapping(value = "/desactiverUser")
public String desactiverUser(Model model,String email){
try {
	System.out.println("Email "+email);
	dao.desactiverUser(email);
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if (principal instanceof UserDetails) {
		String username = ((UserDetails) principal).getUsername();
		
		dao.addTrace(new TraceSysteme(username, "Desactiver user", new Date(), new Date()));
			}
	listerusers(model);
}catch(Exception e) {
	
}
return"lister_user";	
}


	@RequestMapping(value = "/reactiverUser")
public String reactiverUser(Model model,String email){
	
try {
	
	dao.reactiverUser(email);
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if (principal instanceof UserDetails) {
		String username = ((UserDetails) principal).getUsername();
		
		dao.addTrace(new TraceSysteme(username, "Reactiver user", new Date(), new Date()));
			}
	listerusers(model);
}catch(Exception e) {
	
}
return"lister_user";	
}


	@RequestMapping(value = "/listerTraces")
public String listerTraces(Model model){
	List<TraceSysteme>listerTraces=dao.listerTrace();
	
	model.addAttribute("traces", listerTraces);
	
return"lister_trace";	
}
	
	

	

	@RequestMapping(value = "/passwordReset")
	public String passwordReset(Model model,String email) {
       System.out.println("Reset Password ok");
		        
		try {
		
		dao.resetPassword(email);
		
		model.addAttribute("succes", "succes");
	
       }catch(Exception e) {
	
	model.addAttribute("faillure", "faillure");
	System.out.println("Error: " + e.getMessage());
}
		return"login";
	}
	
	
	
	@RequestMapping(value = "/listerRoles_users")
public String listerRoles_users(Model model,String email,HttpSession session) {
	
	try {
		  Utilisateur u=dao.findUser(email);
		  List<Role> roles_users=u.getRoles(); 
		
		  model.addAttribute("roles_users", roles_users);
		  session.setAttribute("emailUser", u.getEmail());
		  model.addAttribute("user",email);
		  
			/*
			 * for(Role r2:roles_users) {
			 * System.out.println("Role name :"+r2.getRoleName());
			 * 
			 * }
			 */
		 
	} catch (Exception e) {
		System.out.println("Error " + e.getMessage());
	}
	return"listerRolesUser";
}
	
	
	
	@RequestMapping(value = "/enleverRole")
public String enleverRole(Model model,String role,HttpSession session) {
	
	try {
		String email=session.getAttribute("emailUser").toString();
		System.out.println("Email " +email);
		
	dao.enleverRole(email,role);
	} catch (Exception e) {
		System.out.println("Error " + e.getMessage());
	}
	return"listerRolesUser";
}
	
	
	
	
	
	
	
	
}
