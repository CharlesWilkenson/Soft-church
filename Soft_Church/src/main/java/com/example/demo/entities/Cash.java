package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue(value = "Cash")
@Entity
public class Cash extends Dime implements Serializable{

	public Cash() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cash(double montant, Date date) {
		super(montant, date);
		// TODO Auto-generated constructor stub
	}

}
