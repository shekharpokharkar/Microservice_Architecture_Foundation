package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.response.AddressResponse;
import com.example.demo.response.EmployeeRequest;
import com.example.demo.response.EmployeeResponse;
import com.example.demo.service.EmployeeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping(name = "/{id}")
	ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id) {

		EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);

		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}

	@GetMapping("/")
	ResponseEntity<List<EmployeeResponse>> getAllEmployeeDetails() {

		List<EmployeeResponse> employeeResponse = employeeService.getAllEmployeeDetails();
		System.out.println("employeeResponse" + employeeResponse);
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}

	@PostMapping("/")
	ResponseEntity<EmployeeResponse> addEmployeeDetails(@RequestBody EmployeeRequest employyee) {

		EmployeeResponse employeeResponse = employeeService.addEmployee(employyee);

		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}

	@GetMapping(value = "/department/{departmentName}")
	ResponseEntity<List<EmployeeResponse>> getAllEmployeeDetailsByUsingDepartmentName(
			@PathVariable("departmentName") String departmentname) {

		List<EmployeeResponse> employeeResponse = employeeService
				.getAllEmployeeDetailsByUsingDepartmentName(departmentname);
		System.out.println("employeeResponse" + employeeResponse);
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}

}
