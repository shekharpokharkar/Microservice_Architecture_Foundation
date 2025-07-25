package com.example.demo.feign;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.DTO.AddressResponse;
import com.example.demo.DTO.EmployeeRequest;
import com.example.demo.DTO.EmployeeResponse;

@Component
public class EmployeeResponseFallBack implements EmployeeFeignClient {

	@Override
	public ResponseEntity<EmployeeResponse> getEmployeeDetails(int id) {

		EmployeeResponse response = new EmployeeResponse();

		return new ResponseEntity<EmployeeResponse>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<EmployeeResponse>> getAllEmployeeDetails() {
		List<EmployeeResponse> empList = new ArrayList<>();

		EmployeeResponse response = new EmployeeResponse();

		empList.add(response);
		return new ResponseEntity<List<EmployeeResponse>>(empList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EmployeeResponse> addEmployeeDetails(EmployeeRequest employyee) {

		EmployeeResponse response = new EmployeeResponse();

		return new ResponseEntity<EmployeeResponse>(response, HttpStatus.OK);
	}

}
