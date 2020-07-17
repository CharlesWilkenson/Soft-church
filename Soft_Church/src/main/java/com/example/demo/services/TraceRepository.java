package com.example.demo.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.TraceSysteme;

public interface TraceRepository extends JpaRepository<TraceSysteme, Long>{

	@Query("SELECT t FROM TraceSysteme t")
	public List<TraceSysteme> listerTrace();
}
