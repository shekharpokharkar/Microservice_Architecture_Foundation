package com.example.demo.DTO;

import java.util.List;

public class DepartmentDTO {

	private String departmentName = "Department Details";

	private List<EmployeeResponse> empList;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<EmployeeResponse> getEmpList() {
		return empList;
	}

	public void setEmpList(List<EmployeeResponse> empList) {
		this.empList = empList;
	}

	@Override
	public String toString() {
		return "DepartmentDTO [departmentName=" + departmentName + ", empList=" + empList + "]";
	}

	public DepartmentDTO() {

	}

}
