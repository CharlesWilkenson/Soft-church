package com.example.demo.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Cheque;

public interface ChequeRepository extends JpaRepository<Cheque, Long>{

}