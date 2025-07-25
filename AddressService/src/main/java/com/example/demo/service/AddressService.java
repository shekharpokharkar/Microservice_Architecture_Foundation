package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Address;
import com.example.demo.repo.AddressRepo;
import com.example.demo.response.AddressRequest;
import com.example.demo.response.AddressResponse;

@Service
public class AddressService {

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private ModelMapper modelMapper;

	public AddressResponse findAddressByEmployeeId(int employeeId) {

		Address address = addressRepo.findAddressByEmployeeId(employeeId);

		System.out.println("address"+address);
		
		AddressResponse addressResponse = modelMapper.map(address, AddressResponse.class);
		System.out.println("addressResponse"+addressResponse);
		return addressResponse;
	}

	public List<AddressResponse> findAllAddress() {

		return addressRepo.findAll().stream().map(add -> modelMapper.map(add, AddressResponse.class)).toList();

	}

	public AddressResponse saveAddress(AddressRequest addressResponse) {

		Address address = modelMapper.map(addressResponse, Address.class);

		Address save = addressRepo.save(address);

		return modelMapper.map(save, AddressResponse.class);
	}
}
