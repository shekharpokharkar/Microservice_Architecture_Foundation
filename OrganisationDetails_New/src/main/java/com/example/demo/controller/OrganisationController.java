package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.OrganisationDetail;
import com.example.demo.service.OrganisationService;

@RestController
@RequestMapping("/organisationservice")
public class OrganisationController {

	private static Logger logger=LoggerFactory.getLogger(OrganisationController.class);
	
	@Autowired
	private OrganisationService service;

	@GetMapping("/")
	public ResponseEntity<OrganisationDetail> getAllOrganisationDetails() {
		
		logger.info("Inside OrganisationController.getAllOrganisationDetails()");

		
		OrganisationDetail details = service.getAllOrgaisationDetail();

		return new ResponseEntity<OrganisationDetail>(details, HttpStatus.OK);
	}

}
