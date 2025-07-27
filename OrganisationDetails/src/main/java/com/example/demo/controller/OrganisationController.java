package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.OrganisationDetail;
import com.example.demo.service.OrganisationService;

@RestController
public class OrganisationController {

	@Autowired
	private OrganisationService service;

	@GetMapping("/org-details-by-id/{id}")
	public ResponseEntity<OrganisationDetail> getOrganisationDetails(@PathVariable int id) {
		
		System.out.println("Id:"+id);
		OrganisationDetail details = service.getEmployeeByIdOrgaisationDetail(id);

		return new ResponseEntity<OrganisationDetail>(details, HttpStatus.OK);
	}

	
}
