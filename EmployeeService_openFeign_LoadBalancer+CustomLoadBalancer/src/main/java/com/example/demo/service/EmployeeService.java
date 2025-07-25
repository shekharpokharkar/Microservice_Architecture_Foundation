package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.feign.AddressFeign;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.response.AddressResponse;
import com.example.demo.response.EmployeeResponse;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo EmployeeRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AddressFeign addressFeign;

	public EmployeeResponse getEmployeeById(int id) {

		// employee -> EmployeeResponse
		Employee employee = EmployeeRepo.findById(id).get(); // db call -> 10

		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);

		// 10 sec

		ResponseEntity<AddressResponse> addressByEmployeeId = addressFeign.getAddressByEmployeeId(id);

		AddressResponse addressResponse = addressByEmployeeId.getBody();
		employeeResponse.setAddressResponse(addressResponse);

		return employeeResponse;

	}

}
