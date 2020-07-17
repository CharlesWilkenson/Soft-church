package com.example.demo.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Espece")
public class Espece extends Don{
	public Espece(String donateur, Date date, double montant, String devise) {
		super(donateur, date);
		this.montant = montant;
		this.devise = devise;
	}
	private static final long serialVersionUID = 1L;
	
	public double getMontant() {
		return montant;
	}
	public Espece() {
		super();
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
	private double montant;
	private String devise;
	
	
	
	
	
	

}
