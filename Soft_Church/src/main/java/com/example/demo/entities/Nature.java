package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Nature")
public class Nature extends Don{

private static final long serialVersionUID = 1L;
	
@Column(name = "Description",length = 255)
private String Description;

public Nature() {
	super();
}

public String getDescription() {
	return Description;
}

public void setDescription(String description) {
	Description = description;
}

public Nature(String donateur, Date date, String description) {
	super(donateur, date);
	Description = description;
}

}
