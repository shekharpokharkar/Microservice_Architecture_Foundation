package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeName {

	@JsonProperty("organisationFounder")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EmployeeName() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmployeeName [name=" + name + "]";
	}

}
