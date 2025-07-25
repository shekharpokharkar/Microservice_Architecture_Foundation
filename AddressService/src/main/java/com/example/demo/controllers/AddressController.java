package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.AddressRequest;
import com.example.demo.response.AddressResponse;
import com.example.demo.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GetMapping("/address/{employeeId}")
	public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int id) {

		AddressResponse addressReponse = null;
		System.out.println("********");
		addressReponse = addressService.findAddressByEmployeeId(id);

		return ResponseEntity.status(HttpStatus.OK).body(addressReponse);

	}

	@GetMapping("/address/")
	public ResponseEntity<List<AddressResponse>> getAllAddress() {

		List<AddressResponse> addressReponse = null;

		addressReponse = addressService.findAllAddress();

		return ResponseEntity.status(HttpStatus.OK).body(addressReponse);

	}

	@PostMapping("/address/")
	public ResponseEntity<AddressResponse> saveAddress(@RequestBody AddressRequest address) {

		AddressResponse addressReponse = null;

		addressReponse = addressService.saveAddress(address);

		return ResponseEntity.status(HttpStatus.OK).body(addressReponse);

	}

}
