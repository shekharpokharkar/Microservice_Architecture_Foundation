package com.example.demo.DTO;

import java.util.List;

public class DepartmentDTO {
	
	
	private String department="DepartmentWise Employee List";
	
	private List<EmployeeResponse> empList;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<EmployeeResponse> getEmpList() {
		return empList;
	}

	public void setEmpList(List<EmployeeResponse> empList) {
		this.empList = empList;
	}

	@Override
	public String toString() {
		return "DepartmentDTO [department=" + department + ", empList=" + empList + "]";
	}

	public DepartmentDTO(String department, List<EmployeeResponse> empList) {
		super();
		this.department = department;
		this.empList = empList;
	}

	public DepartmentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
