package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.entity.Employee;
import com.example.demo.feign.AddressFeign;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.response.AddressRequest;
import com.example.demo.response.AddressResponse;
import com.example.demo.response.EmployeeRequest;
import com.example.demo.response.EmployeeResponse;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo EmployeeRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private WebClient webClient;

	@Autowired
	private AddressFeign addressFeign;

	// @Autowired
	// private RestTemplate restTemplate; // null

	// @Value("${adressservice.base.url}")
	// private String addressBaseURL;

//	public EmployeeService(@Value("${adressservice.base.url}") String addressBaseURL,
//			RestTemplateBuilder builder) {
//		
//		this.restTemplate= builder
//				           .rootUri(addressBaseURL)
//				           .build();
//	}

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

	private AddressResponse AddressResponseFromWebClient(int id) {
		return webClient.get().uri("/address/" + id).retrieve().bodyToMono(AddressResponse.class).block();
	}

	public EmployeeResponse addEmployee(EmployeeRequest employyee) {

		Employee employee = modelMapper.map(employyee, Employee.class);

		Employee employee2 = EmployeeRepo.save(employee);

		AddressResponse addressResponse = employyee.getAddressResponse();

		AddressRequest addressRequest = modelMapper.map(addressResponse, AddressRequest.class);
		addressRequest.setEmployeeId(employee2.getId());
		ResponseEntity<AddressResponse> saveAddress = addressFeign.saveAddress(addressRequest);

		EmployeeResponse employeeResponse = modelMapper.map(employee2, EmployeeResponse.class);
		employeeResponse.setAddressResponse(saveAddress.getBody());
		return employeeResponse;
	}

	public List<EmployeeResponse> getAllEmployeeDetails() {

		List<Employee> employeeDetails = EmployeeRepo.findAll();

		List<EmployeeResponse> empResponse = Arrays.asList(modelMapper.map(employeeDetails, EmployeeResponse[].class));
		ResponseEntity<List<AddressResponse>> allAddress = addressFeign.getAllAddress();

		List<AddressResponse> addressResponse = allAddress.getBody();
		for (EmployeeResponse emp : empResponse) {

			for (AddressResponse add : addressResponse) {
				if (emp.getId() == add.getEmployeeId()) {
					emp.setAddressResponse(add);
				}
			}
		}

		return empResponse;
	}

//	private AddressResponse callingAddressServiceUsingRESTTemplate(int id) {
//		return restTemplate.getForObject("/address/{id}", AddressResponse.class , id);
//	}

}
