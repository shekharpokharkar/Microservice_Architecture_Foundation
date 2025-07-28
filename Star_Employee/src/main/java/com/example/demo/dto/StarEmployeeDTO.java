package com.example.demo.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("selexp")
public class StarEmployeeDTO {

	private String starEmployeeOfTheMonth = "Mr/Miss";
	private String department = "XYZ";

	public String getStarEmployeeOfTheMonth() {
		return starEmployeeOfTheMonth;
	}

	public void setStarEmployeeOfTheMonth(String starEmployeeOfTheMonth) {
		this.starEmployeeOfTheMonth = starEmployeeOfTheMonth;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public StarEmployeeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
