package com.example.demo.entities;

import java.util.List;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
	
	@Id
	@Column(name = "role")
private String roleName;
@ManyToMany(cascade =CascadeType.REMOVE,mappedBy = "roles",fetch =FetchType.EAGER)
private List<Utilisateur> users;
	
	
	public Role() {
		super();
	
	}
	public Role(String roleName) {
		super();
		this.roleName = roleName;
	}
	public Role(String roleName, List<Utilisateur> users) {
		super();
		this.roleName = roleName;
		this.users = users;
	}
	public String getRoleName() {
		return roleName;
	}
	public List<Utilisateur> getUsers() {
		return users;
	}
	public void setUsers(List<Utilisateur> users) {
		this.users = users;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public void ListRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<Utilisateur> getUser() {
		return users;
	}
	public void ListUser(List<Utilisateur> users) {
		this.users = users;
	}

}
