package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue(value = "Cheque")
@Entity
public class Cheque extends Dime implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public String getNumCheque() {
		return numCheque;
	}
	public void setNumCheque(String numCheque) {
		this.numCheque = numCheque;
	}
	public String getBanque() {
		return banque;
	}
	public Cheque(double montant, Date date, String numCheque, String banque) {
		super(montant, date);
		this.numCheque = numCheque;
		this.banque = banque;
	}
	public Cheque() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cheque(double montant, Date date) {
		super(montant, date);
		// TODO Auto-generated constructor stub
	}
	public void setBanque(String banque) {
		this.banque = banque;
	}
private String numCheque;
private String banque;
}
