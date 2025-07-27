package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.EmployeeName;
import com.example.demo.DTO.OrganisationDetail;
import com.example.demo.feign.EmployeeFeignClient;
import com.example.demo.feign.OrganisationFeignClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrganisationDetailsImpl implements OrganisationService {

	@Autowired
	private EmployeeFeignClient employeeFeign;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private OrganisationFeignClient organisationFeign;

	@CircuitBreaker(name = "Orgshekhar", fallbackMethod = "fallBack_getEmployeeByIdOrgaisationDetail")
	@Override
	public OrganisationDetail getEmployeeByIdOrgaisationDetail(int id) {
		OrganisationDetail detail = new OrganisationDetail();

		ResponseEntity<OrganisationDetail> organisationDetails = organisationFeign.getAllOrganisationDetails();
		OrganisationDetail body = organisationDetails.getBody();
		System.out.println("body"+body);
		EmployeeName employeeDetail = mapper.map(body, EmployeeName.class);

		detail.setEmployee(employeeDetail);
		return detail;
	}

	public OrganisationDetail fallBack_getEmployeeByIdOrgaisationDetail(int id, Throwable t) {
		OrganisationDetail detail = new OrganisationDetail();

		detail.setEmployee(new EmployeeName());
		return detail;
	}

	/*
	 * @CircuitBreaker(name = "employeeservice", fallbackMethod =
	 * "myCustomOrganisationDetails")
	 * 
	 * @Override public OrganisationDetail getAllOrgaisationDetail() {
	 * OrganisationDetail detail = new OrganisationDetail();
	 * ResponseEntity<List<EmployeeResponse>> allEmployeeDetails =
	 * employeeFeign.getAllEmployeeDetails();
	 * 
	 * System.out.println("allEmployeeDetails" + allEmployeeDetails.getBody());
	 * List<EmployeeDetail> asList =
	 * Arrays.asList(mapper.map(allEmployeeDetails.getBody(),
	 * EmployeeDetail[].class));
	 * 
	 * detail.setEmployee(asList); return detail; }
	 * 
	 * 
	 * public OrganisationDetail myCustomOrganisationDetails(Throwable t) {
	 * System.err.println("Fallback triggered due to: " +
	 * t.getClass().getSimpleName() + " - " + t.getMessage());
	 * System.out.println("*************************"); OrganisationDetail detail =
	 * new OrganisationDetail(); System.out.println(t.getMessage());
	 * List<EmployeeDetail> asList = new ArrayList<>(); asList.add(new
	 * EmployeeDetail()); detail.setEmployee(asList); return detail; }
	 * 
	 * 
	 * public OrganisationDetail myCustomOrganisationDetails(Throwable t) {
	 * System.err.println("🔴 OrganisationService fallback triggered!");
	 * System.err.println("Reason: " + t.getClass().getSimpleName() + " - " +
	 * t.getMessage());
	 * 
	 * OrganisationDetail detail = new OrganisationDetail(); List<EmployeeDetail>
	 * asList = new ArrayList<>();
	 * 
	 * EmployeeDetail dummy = new EmployeeDetail(); dummy.setId(0);
	 * dummy.setName("Fallback Emp"); dummy.setEmail("no-emp@fallback.com");
	 * dummy.setBloodGroup("N/A"); asList.add(dummy);
	 * 
	 * detail.setEmployee(asList); return detail; }
	 */

	/*
	 * @CircuitBreaker(name = "employeeservice", fallbackMethod =
	 * "fallBack_getEmployeeByIdOrgaisationDetail")
	 * 
	 * @Override public OrganisationDetail getEmployeeByIdOrgaisationDetail(int id)
	 * { OrganisationDetail detail = new OrganisationDetail(); List<EmployeeDetail>
	 * detailsList = new ArrayList<>(); ResponseEntity<EmployeeResponse>
	 * employeeDetails = employeeFeign.getEmployeeDetails(id); EmployeeResponse body
	 * = employeeDetails.getBody();
	 * 
	 * EmployeeDetail employeeDetail = mapper.map(body, EmployeeDetail.class);
	 * 
	 * detailsList.add(employeeDetail); detail.setEmployee(detailsList); return
	 * detail; }
	 * 
	 * public OrganisationDetail fallBack_getEmployeeByIdOrgaisationDetail(int id,
	 * Throwable t) { OrganisationDetail detail = new OrganisationDetail();
	 * List<EmployeeDetail> detailsList = new ArrayList<>(); detailsList.add(new
	 * EmployeeDetail()); detail.setEmployee(detailsList); return detail; }
	 */

}
