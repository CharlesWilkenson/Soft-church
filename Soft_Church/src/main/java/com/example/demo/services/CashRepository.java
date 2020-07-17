package com.example.demo.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Cash;

public interface CashRepository extends JpaRepository<Cash, Long>{

}
