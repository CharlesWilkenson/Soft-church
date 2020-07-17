package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue(value ="Culte")
public class Culte extends Activite implements Serializable{
	/*
	 * @OneToOne(mappedBy = "culte") private Offrandes ofrrande;
	 */

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




public Culte() {
	super();
	// TODO Auto-generated constructor stub
}




public Culte(Long id) {
	super(id);
	// TODO Auto-generated constructor stub
}




public Culte(String debut, String fin, Date date, List<Membre> membres) {
	super(debut, fin, date, membres);
	// TODO Auto-generated constructor stub
}







public Culte(String debut, String fin, Date date, Offrande offrande) {
	super(debut, fin, date, offrande);
	// TODO Auto-generated constructor stub
}




public Culte(String debut, String fin, Date date) {
	super(debut, fin, date);
}


}
