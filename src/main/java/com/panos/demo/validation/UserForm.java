package com.panos.demo.validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserForm {
	
	@NotNull
	@Size(min=1)
	private Long Id;
	
	@NotNull
	private String firstname;
	
	@NotNull
	private String surename;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurename() {
		return surename;
	}

	public void setSurename(String surename) {
		this.surename = surename;
	}

	@Override
	public String toString() {
		return "UserForm [Id=" + Id + ", firstname=" + firstname + ", surename=" + surename + "]";
	}


}
