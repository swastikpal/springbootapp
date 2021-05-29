package com.gcdc.sample.ldap.model;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

@Entry(base = "ou=user", objectClasses = {"person", "inetOrgPerson", "top"})
public class PetStoreAdmin {

	@Id
	private Name id;
	
	@Attribute(name = "cn")
	private String username;
	
	@Attribute(name = "sn")
	private String password;
	
	public PetStoreAdmin() {
	}

	public PetStoreAdmin(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Name getId() {
		return id;
	}

	public void setId(Name id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
