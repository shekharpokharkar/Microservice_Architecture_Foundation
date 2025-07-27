package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.EmployeeDetail;
import com.example.demo.DTO.EmployeeResponse;
import com.example.demo.DTO.OrganisationDetail;
import com.example.demo.feign.EmployeeFeign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrganisationDetailsImpl implements OrganisationService {

	@Autowired
	private EmployeeFeign feign;

	@Autowired
	private ModelMapper mapper;

	@Override
	@CircuitBreaker(name = "employee-service", fallbackMethod = "FallBack_getAllOrgaisationDetail")
	public OrganisationDetail getAllOrgaisationDetail() {
		OrganisationDetail details = new OrganisationDetail();
		List<EmployeeDetail> ilist = new ArrayList<>();
		ResponseEntity<List<EmployeeResponse>> allEmployeeDetails = feign.getAllEmployeeDetails();
		List<EmployeeResponse> body = allEmployeeDetails.getBody();

		body.forEach(s -> {

			EmployeeDetail detail = new EmployeeDetail();
			mapper.map(s, detail);
			ilist.add(detail);
		});

		details.setEmployee(ilist);
		return details;
	}

	public OrganisationDetail FallBack_getAllOrgaisationDetail(Throwable t) {
		OrganisationDetail details = new OrganisationDetail();
		List<EmployeeDetail> ilist = new ArrayList<>();
		ilist.add(new EmployeeDetail());
		return details;
	}

}
