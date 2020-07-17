package com.example.demo.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Nature;

public interface NatureRepository extends JpaRepository<Nature, Long>{

}
