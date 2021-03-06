package com.ganesh.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@Column(name="user_id")
	@GeneratedValue
	private Integer id;
	
	@Column(name="user_name")
	private String name;
	
	@Column(name="user_password")
	private String password;
	
	@Column(name="user_email")
	private String email;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="roles", joinColumns = @JoinColumn(name="user_id"))
	@Column(name="user_role")
	private List<String> roles;
	
	
	public User() {
	}
	

	public User(String name, String password, String email, List<String> roles) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
	

}
