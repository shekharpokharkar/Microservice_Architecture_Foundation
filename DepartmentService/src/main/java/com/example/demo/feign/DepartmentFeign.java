package com.example.demo.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.DTO.EmployeeResponse;
@FeignClient(name = "employeeservice", path = "/employee-app/api")
public interface DepartmentFeign {

	@GetMapping("/employees/")
	ResponseEntity<List<EmployeeResponse>> getAllEmployeeDetails();

	@GetMapping(value = "/employees/department/{departmentName}")
	ResponseEntity<List<EmployeeResponse>> getAllEmployeeDetailsByUsingDepartmentName(
			@PathVariable("departmentName") String departmentname);

}
