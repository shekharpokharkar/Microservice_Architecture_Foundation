package com.example.demo.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.DTO.EmployeeRequest;
import com.example.demo.DTO.EmployeeResponse;

@FeignClient(name = "employee-service")
public interface EmployeeFeign {
	@GetMapping("/employees/{id}")
	ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id);

	@GetMapping("/employees/")
	ResponseEntity<List<EmployeeResponse>> getAllEmployeeDetails();

	@PostMapping("/employees/")
	ResponseEntity<EmployeeResponse> addEmployeeDetails(@RequestBody EmployeeRequest employyee);
}
