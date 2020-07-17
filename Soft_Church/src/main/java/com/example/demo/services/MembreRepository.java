package com.example.demo.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Membre;


public interface MembreRepository extends JpaRepository<Membre, String>{

	@Query("SELECT m from Membre m")
	public List<Membre> listerMembre();
	
	@Query("SELECT m from Membre m where m.email=:x")
	public List<Membre> listerMembre(@Param("x")String email);
}
