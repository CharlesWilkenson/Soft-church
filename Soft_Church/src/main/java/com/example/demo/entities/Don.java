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
import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type",discriminatorType = DiscriminatorType.STRING,length = 10)
public class Don implements Serializable{

	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
private String donateur;

@javax.persistence.Temporal(TemporalType.DATE)
private Date date;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getDonateur() {
	return donateur;
}

public void setDonateur(String donateur) {
	this.donateur = donateur;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public Don() {
	super();
	// TODO Auto-generated constructor stub
}

public Don(String donateur, Date date) {
	super();
	this.donateur = donateur;
	this.date = date;
}

	
}
