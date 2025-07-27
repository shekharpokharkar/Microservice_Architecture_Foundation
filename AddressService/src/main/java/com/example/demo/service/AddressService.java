package com.example.demo.service;

import java.util.Arrays;
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

	public List<AddressResponse> findAllAddress() {

		List<Address> all = addressRepo.findAll();

		AddressResponse[] map = modelMapper.map(all, AddressResponse[].class);

		List<AddressResponse> asList = Arrays.asList(map);
		return asList;

	}

	public AddressResponse saveAddress(AddressRequest addressResponse) {

		Address address = modelMapper.map(addressResponse, Address.class);

		Address save = addressRepo.save(address);

		return modelMapper.map(save, AddressResponse.class);
	}

	public AddressResponse findAddressByEmployeeId(int employeeId) {

		Address address = addressRepo.findAddressByEmployeeId(employeeId);

		System.out.println("address" + address);

		AddressResponse addressResponse = modelMapper.map(address, AddressResponse.class);
		System.out.println("addressResponse" + addressResponse);
		return addressResponse;
	}
}
