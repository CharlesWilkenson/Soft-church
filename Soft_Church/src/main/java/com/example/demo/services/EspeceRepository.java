package com.example.demo.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Espece;

public interface EspeceRepository extends JpaRepository<Espece, Long>{

}
