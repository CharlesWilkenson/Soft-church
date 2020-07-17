package com.example.demo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Utilisateur {
@Id
@Column(name = "username",length=50,nullable=false)
private String email;

@Column(length=255,nullable=false)
private String password;

@OneToOne(fetch = FetchType.EAGER)
private Membre membre;

@ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
@JoinTable(
		  name = "user_roles", 
		  joinColumns = @JoinColumn(name = "user_id"), 
		  inverseJoinColumns = @JoinColumn(name = "role_id"))
          private List<Role> roles;

@Column(name = "active")
private boolean etat;




public Utilisateur(String email, String password, Membre membre, boolean etat) {
	super();
	this.email = email;
	this.password = password;
	this.membre = membre;
	this.etat = etat;
}



public boolean isEtat() {
	return etat;
}



public void setEtat(boolean etat) {
	this.etat = etat;
}



public void setEmail(String email) {
	this.email = email;
}

public void setPassword(String password) {
	this.password = password;
}

public void setMembre(Membre membre) {
	this.membre = membre;
}


public Utilisateur() {
	super();
	// TODO Auto-generated constructor stub
}



public String getEmail() {
	return email;
}

public void ListEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void ListPassword(String password) {
	this.password = password;
}

public Membre getMembre() {
	return membre;
}

public void ListMembre(Membre membre) {
	this.membre = membre;
}



public List<Role> getRoles() {
	return roles;
}



public void setRoles(List<Role> roles) {
	this.roles = roles;
}


}



