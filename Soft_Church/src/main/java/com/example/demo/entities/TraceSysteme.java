package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TraceSysteme implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String user;
	private String operation;
	@Temporal(TemporalType.DATE)
	private Date dateOperation;
	@Temporal(TemporalType.TIME)
	private Date heureOperation;
	public TraceSysteme(Long id, String user, String operation, Date dateOperation) {
		super();
		this.id = id;
		this.user = user;
		this.operation = operation;
		this.dateOperation = dateOperation;
	}
	public TraceSysteme(String user, String operation, Date dateOperation, Date heureOperation) {
		super();
		this.user = user;
		this.operation = operation;
		this.dateOperation = dateOperation;
		this.heureOperation = heureOperation;
	}
	public Date getHeureOperation() {
		return heureOperation;
	}
	public void setHeureOperation(Date heureOperation) {
		this.heureOperation = heureOperation;
	}
	public TraceSysteme(String user, String operation, Date dateOperation) {
		super();
		this.user = user;
		this.operation = operation;
		this.dateOperation = dateOperation;
	}
	public TraceSysteme() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Date getDateOperation() {
		return dateOperation;
	}
	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

}
