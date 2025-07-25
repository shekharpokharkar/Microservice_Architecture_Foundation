package com.example.demo.feign;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.response.AddressRequest;
import com.example.demo.response.AddressResponse;

@Component
public class FallBackMyAddressFeign implements AddressFeign {

	@Override
	public ResponseEntity<AddressResponse> getAddressByEmployeeId(int id) {

		AddressResponse response = new AddressResponse();

		return new ResponseEntity<AddressResponse>(response, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<List<AddressResponse>> getAllAddress() {
		List<AddressResponse> addlist = new ArrayList<>();
		addlist.add(new AddressResponse());
		return new ResponseEntity<List<AddressResponse>>(addlist, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AddressResponse> saveAddress(AddressRequest address) {

		AddressResponse response = new AddressResponse();

		return new ResponseEntity<AddressResponse>(response, HttpStatus.OK);
	}

}
