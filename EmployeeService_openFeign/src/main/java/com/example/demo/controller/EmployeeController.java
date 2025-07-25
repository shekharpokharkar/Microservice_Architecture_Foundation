package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.EmployeeRequest;
import com.example.demo.response.EmployeeResponse;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/employees/{id}")
	ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id) {

		EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);

		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}

	@GetMapping("/employees/")
	ResponseEntity<List<EmployeeResponse>> getAllEmployeeDetails() {

		List<EmployeeResponse> employeeResponse = employeeService.getAllEmployeeDetails();
		System.out.println("employeeResponse"+employeeResponse );
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}

	@PostMapping("/employees/")
	ResponseEntity<EmployeeResponse> addEmployeeDetails(@RequestBody EmployeeRequest employyee) {

		EmployeeResponse employeeResponse = employeeService.addEmployee(employyee);

		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}

}
