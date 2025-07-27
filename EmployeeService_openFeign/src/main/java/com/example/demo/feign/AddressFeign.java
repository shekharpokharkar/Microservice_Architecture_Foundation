package com.example.demo.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.response.AddressRequest;
import com.example.demo.response.AddressResponse;

@FeignClient(name = "address-service")
public interface AddressFeign {

	@GetMapping("/address/{employeeId}")
	public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int id);

	@GetMapping("/address/")
	public ResponseEntity<List<AddressResponse>> getAllAddress();

	@PostMapping("/address/")
	public ResponseEntity<AddressResponse> saveAddress(@RequestBody AddressRequest address);

}
