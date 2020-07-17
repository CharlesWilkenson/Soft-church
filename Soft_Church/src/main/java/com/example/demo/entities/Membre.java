package com.example.demo.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TemporalType;

import javax.persistence.Temporal;

@Entity
public class Membre {
	@Id
	private String code;
	@Column(length=255,nullable=false)
	private String nom;
	@Column(length=50,nullable=false)
	private String prenom;
	@Column(length=10,nullable=false)
	private String sexe;
	@Column(length=20,nullable=false)
	private String tel;
	@Column(length=50,nullable=false)
	private String nationalite;
	@Column(length=50,nullable=false)
	private String email;
	@Column(length=100,nullable=false)
	private String adresse;
	@Column(length=20,nullable=false)
	private String dateNaissance;
	@Column(length=50,nullable=false)
	private String lieuNaissance;
	@Column(length=20,nullable=false)
	private String StatutMatrimonial;
	@Column(length=20,nullable=false)
	private String identification;
	@Lob
	private byte[] photo;	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "membre", cascade = CascadeType.ALL)
	private Utilisateur user;
	
	@Temporal(TemporalType.DATE)
	@Column(length=50,nullable=false)
	private Date DateAjout;
	
	@ManyToMany(mappedBy = "membres",cascade=CascadeType.ALL)
	private List<Activite> activites;
	
	@OneToMany(mappedBy = "membre")
	private List<Dime> dimes;
	
	
	
	
	
	public List<Dime> getDimes() {
		return dimes;
	}




	public void setDimes(List<Dime> dimes) {
		this.dimes = dimes;
	}




	public List<Activite> getActivites() {
		return activites;
	}




	public void setActivites(List<Activite> activites) {
		this.activites = activites;
	}




	public Membre(String code, String nom, String prenom, String sexe, String tel, String nationalite, String email,
			String adresse, String dateNaissance, String lieuNaissance, String statutMatrimonial, String identification,
			byte[] photo, Date dateAjout) {
		super();
		this.code = code;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.tel = tel;
		this.nationalite = nationalite;
		this.email = email;
		this.adresse = adresse;
		this.dateNaissance = dateNaissance;
		this.lieuNaissance = lieuNaissance;
		StatutMatrimonial = statutMatrimonial;
		this.identification = identification;
		this.photo = photo;
		DateAjout = dateAjout;
	}




	public String getNationalite() {
		return nationalite;
	}




	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}




	public Date getDateAjout() {
		return DateAjout;
	}




	public void setDateAjout(Date dateAjout) {
		DateAjout = dateAjout;
	}




	public Membre(String code) {
		super();
		this.code = code;
	}

	
	
	
	public Membre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getLieuNaissance() {
		return lieuNaissance;
	}
	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}
	public String getStatutMatrimonial() {
		return StatutMatrimonial;
	}
	public void setStatutMatrimonial(String statutMatrimonial) {
		StatutMatrimonial = statutMatrimonial;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public Utilisateur getUser() {
		return user;
	}
	public void setUser(Utilisateur user) {
		this.user = user;
	}
	

	

}
