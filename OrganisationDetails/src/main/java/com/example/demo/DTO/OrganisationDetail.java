package com.example.demo.DTO;

public class OrganisationDetail {

	private String organisationName = "SeleniumExpress";
	private String organisationFounder = "Abhilash Panigrahi";
	private String organisationSince = "2015";
	private EmployeeName employee;

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getOrganisationFounder() {
		return organisationFounder;
	}

	public void setOrganisationFounder(String organisationFounder) {
		this.organisationFounder = organisationFounder;
	}

	public String getOrganisationSince() {
		return organisationSince;
	}

	public void setOrganisationSince(String organisationSince) {
		this.organisationSince = organisationSince;
	}

	public OrganisationDetail() {

	}

	public EmployeeName getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeName employee) {
		this.employee = employee;
	}

}
