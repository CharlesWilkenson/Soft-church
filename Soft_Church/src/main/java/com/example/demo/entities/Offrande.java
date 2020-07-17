package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Offrande implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double montant;
	private String devise;
	@OneToOne
	@JoinColumn(name = "Activite",nullable = false)
	private Activite activite;
	
	
	public Offrande() {
		super();
		
	}
	public Long getId() {
		return id;
	}
	public Offrande(double montant, String devise, Activite activite) {
		super();
		this.montant = montant;
		this.devise = devise;
		this.activite = activite;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Activite getActivite() {
		return activite;
	}
	public void setActivite(Activite activite) {
		this.activite = activite;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getDevise() {
		return devise;
	}
	public void setDevise(String devise) {
		this.devise = devise;
	}

	
}
