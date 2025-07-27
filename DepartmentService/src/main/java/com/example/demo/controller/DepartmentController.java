package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.DepartmentDTO;
import com.example.demo.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/{department}")
	public ResponseEntity<DepartmentDTO> getAllEmployeeDepartment(@PathVariable("department") String department) {
		DepartmentDTO allEmployeeInGivenDepartment = departmentService.getAllEmployeeInGivenDepartment(department);

		return new ResponseEntity<DepartmentDTO>(allEmployeeInGivenDepartment, HttpStatus.OK);
	}

}
