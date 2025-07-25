package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.EmployeeDetail;
import com.example.demo.DTO.EmployeeResponse;
import com.example.demo.DTO.OrganisationDetail;
import com.example.demo.feign.EmployeeFeignClient;

@Service
public class OrganisationDetailsImpl implements OrganisationService {

	@Autowired
	private EmployeeFeignClient employeeFeign;

	@Autowired
	private ModelMapper mapper;

	@Override
	public OrganisationDetail getAllOrgaisationDetail() {
		OrganisationDetail detail = new OrganisationDetail();
		ResponseEntity<List<EmployeeResponse>> allEmployeeDetails = employeeFeign.getAllEmployeeDetails();
		
		System.out.println("allEmployeeDetails"+allEmployeeDetails.getBody());
		List<EmployeeDetail> asList = Arrays.asList(mapper.map(allEmployeeDetails.getBody(), EmployeeDetail[].class));
		
		detail.setEmployee(asList);
		return detail;
	}

}
