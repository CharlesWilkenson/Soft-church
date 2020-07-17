package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type",length = 10,discriminatorType = DiscriminatorType.STRING)
@Entity
public abstract class Dime implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
	private double montant;
	
	@javax.persistence.Temporal(TemporalType.DATE)
	private Date date;
	private String devise;


@ManyToOne
private Membre membre;



public Dime(double montant, Date date, String devise,Membre membre) {
	super();
	this.montant = montant;
	this.date = date;
	this.devise = devise;
	this.membre = membre;
}

public String getDevise() {
		return devise;
	}

	public void setDevise(String devise) {
		this.devise = devise;
	}

public Dime(double montant, Date date) {
		super();
		this.montant = montant;
		this.date = date;
	}

public Dime() {
		super();
		// TODO Auto-generated constructor stub
	}

public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}


}
