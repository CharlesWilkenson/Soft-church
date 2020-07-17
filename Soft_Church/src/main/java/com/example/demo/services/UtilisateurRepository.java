package com.example.demo.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Utilisateur;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, String>{

	
	  @Query("SELECT u FROM Utilisateur u WHERE u.email=:x") public
	  List<Utilisateur> listerUser(@Param("x")String email);
	 
	
	  @Query("SELECT u FROM Utilisateur u ") public List<Utilisateur> listerUser();
	 
}
