package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.DepartmentDTO;
import com.example.demo.DTO.EmployeeResponse;
import com.example.demo.feign.DepartmentFeign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private DepartmentFeign departmentFeign;

	@CircuitBreaker(name = "employeeservice", fallbackMethod = "fallBack_getAllEmployeeInGivenDepartment")
	@Override
	public DepartmentDTO getAllEmployeeInGivenDepartment(String department) {
		
			System.out.println("Calling employee service for department: " + department);

			ResponseEntity<List<EmployeeResponse>> response = departmentFeign
					.getAllEmployeeDetailsByUsingDepartmentName(department);

			List<EmployeeResponse> employees = response.getBody();
			List<EmployeeResponse> dtoList = new ArrayList<>();

			for (EmployeeResponse e : employees) {
				dtoList.add(mapper.map(e, EmployeeResponse.class));
			}

			DepartmentDTO dto = new DepartmentDTO();
			dto.setEmpList(dtoList);
			return dto;

		
	}

	public DepartmentDTO fallBack_getAllEmployeeInGivenDepartment(String department, Throwable t) {
		System.err.println("Fallback triggered due to: " + t.getMessage());

		DepartmentDTO fallback = new DepartmentDTO();
		List<EmployeeResponse> emp = new ArrayList<>();
		emp.add(new EmployeeResponse());
		fallback.setEmpList(emp);
		return fallback;
	}

}
