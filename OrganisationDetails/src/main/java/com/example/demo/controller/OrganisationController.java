package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.OrganisationDetail;
import com.example.demo.service.OrganisationService;

@RestController
public class OrganisationController {
	
	@Autowired
	private OrganisationService service;

	@GetMapping("/organisation")
	public ResponseEntity<OrganisationDetail> getAllOrganisationDetails()
	{
		OrganisationDetail details=service.getAllOrgaisationDetail();
		
		return new ResponseEntity<OrganisationDetail>(details,HttpStatus.OK);
	}
}
