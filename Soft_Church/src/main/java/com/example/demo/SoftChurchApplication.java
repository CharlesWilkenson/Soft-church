package com.example.demo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entities.Culte;
import com.example.demo.entities.Membre;
import com.example.demo.entities.Role;
import com.example.demo.entities.Utilisateur;
import com.example.demo.services.CulteRepository;
import com.example.demo.services.RoleRepository;
import com.example.demo.services.Services;
import com.example.demo.services.UtilisateurRepository;

@SpringBootApplication
public class SoftChurchApplication {
	@Autowired
	private Services dao;


	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SoftChurchApplication.class, args);
		System.out.println("Password 1"+encrytePassword("wilki"));
	}

	// System.out.println("Password 1"+encrytePassword("wilki"));
	// System.out.println("Password
	// 2"+"$2a$10$VRVEo3MdNlO9nBm81ujKqusNxXngiwsnmu37ofjVP0yZBDySdQAxC");



	public static String encrytePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
}
