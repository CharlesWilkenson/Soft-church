package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.jpa.repository.Temporal;


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="Type",
discriminatorType =DiscriminatorType.STRING,length = 10 )
@Entity
public abstract  class Activite implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public Activite(Long id) {
		super();
		this.id = id;
	}

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String debut;
private String fin;
@javax.persistence.Temporal(TemporalType.DATE)
private Date date;

//@OneToOne(mappedBy = "activite",cascade = CascadeType.ALL)
@Transient
private Offrande offrande;

@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name ="Presence", joinColumns =@JoinColumn(name="activite_id") 
,inverseJoinColumns = @JoinColumn(name = "membre_id"))
private List<Membre> membres;

public Offrande getOffrande() {
	return offrande;
}



public void setOffrande(Offrande offrande) {
	this.offrande = offrande;
}

public Activite(String debut, String fin, Date date, Offrande offrande) {
	super();
	this.debut = debut;
	this.fin = fin;
	this.date = date;
	this.offrande = offrande;
}

public Activite(String debut, String fin, Date date, List<Membre> membres) {
	super();
	this.debut = debut;
	this.fin = fin;
	this.date = date;
	this.membres = membres;
}



public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}



public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public List<Membre> getMembre() {
	return membres;
}

public void setMembre(List<Membre> membres) {
	this.membres = membres;
}

public Activite() {
	super();
	// TODO Auto-generated constructor stub
}

public Activite(String debut, String fin, Date date) {
	super();
	
	this.debut = debut;
	this.fin = fin;
	this.date = date;
	
}

public String getDebut() {
	return debut;
}

public void setDebut(String debut) {
	this.debut = debut;
}

public String getFin() {
	return fin;
}

public void setFin(String fin) {
	this.fin = fin;
}

public List<Membre> getMembres() {
	return membres;
}

public void setMembres(List<Membre> membres) {
	this.membres = membres;
}




}
