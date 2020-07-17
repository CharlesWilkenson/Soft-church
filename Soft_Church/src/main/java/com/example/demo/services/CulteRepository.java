package com.example.demo.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Culte;

public interface CulteRepository extends JpaRepository<Culte, Long>{
	@Query("SELECT c FROM Culte c WHERE c.id=:x")
	public List<Culte> listerCultes(@Param("x")Long id);
}
