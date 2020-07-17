package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.Membre;
import com.example.demo.services.Services;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/soft_church")
public class RestController {
	@Autowired
	private Services dao;
	
	 @RequestMapping("/getMembrefddd/{code}")
	    public RestResponse getMembre(@PathVariable String code) {
	        //instantiate the response object
	        RestResponse response = new RestResponse();
	     	 System.out.println("Ajax Method is called ");
	        //set the employee to null
	   // Employee returnedEmployee = null;
	     	Membre membre=null;
	     	 try {
	          membre=dao.findMembre("001");
	     	 }catch(Exception e) {}
	        //grab all employees
	   //     List<Employee> allEmployees = getAllEmployees();
	         
	        //look for a match
	    /*    for (Employee employee : allEmployees) {
	            if (employee.getEmployeeId().equals(employeeId)) {
	                returnedEmployee = employee;
	                break;
	            }
	        }*/
	         
	        if (membre == null) {
	            //the URL contains an unknown employee id - we'll return an empty response
	            response.setResponseStatus(RestResponse.NOT_FOUND);
	            response.setResponse("");
	        } else {
	        	 System.out.println("Nom Complet "+membre.getNom()+" "+membre.getPrenom());
	            //good response if we get here
	            response.setResponseStatus(RestResponse.OK);
	            response.setResponse(membre);
	        }
	         
	        return response;
	    }
}
