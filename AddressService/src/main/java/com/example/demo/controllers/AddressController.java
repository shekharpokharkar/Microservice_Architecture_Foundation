package com.example.demo.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.AddressRequest;
import com.example.demo.response.AddressResponse;
import com.example.demo.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	
	private static Logger logger=LoggerFactory.getLogger(AddressController.class);
	@Autowired
	private AddressService addressService;

	@GetMapping("/{employeeId}")
	public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int id) {
		logger.info("Inside  getAddressByEmployeeId");
		AddressResponse addressReponse = null;
		System.out.println("********");
		addressReponse = addressService.findAddressByEmployeeId(id);

		return ResponseEntity.status(HttpStatus.OK).body(addressReponse);

	}

	@GetMapping("/")
	public ResponseEntity<List<AddressResponse>> getAllAddress() {

		List<AddressResponse> addressReponse = null;

		addressReponse = addressService.findAllAddress();

		return ResponseEntity.status(HttpStatus.OK).body(addressReponse);

	}

	@PostMapping("/")
	public ResponseEntity<AddressResponse> saveAddress(@RequestBody AddressRequest address) {

		AddressResponse addressReponse = null;

		addressReponse = addressService.saveAddress(address);

		return ResponseEntity.status(HttpStatus.OK).body(addressReponse);

	}

}
