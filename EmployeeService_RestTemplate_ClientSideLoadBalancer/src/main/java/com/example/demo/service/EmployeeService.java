package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

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
	private WebClient webClient;

	@Autowired
	private AddressFeign addressFeign;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private LoadBalancerClient loadBalancer;

	// @Value("${adressservice.base.url}")
	// private String addressBaseURL;

	/*
	 * public EmployeeService(@Value("${adressservice.base.url}") String
	 * addressBaseURL, RestTemplateBuilder builder) {
	 * 
	 * this.restTemplate = builder.rootUri(addressBaseURL).build(); }
	 */

	public EmployeeResponse getEmployeeById(int id) {

		// employee -> EmployeeResponse
		Employee employee = EmployeeRepo.findById(id).get(); // db call -> 10

		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);

		// 10 sec

		// ResponseEntity<AddressResponse> addressByEmployeeId
		// =addressFeign.getAddressByEmployeeId(id); AddressResponse addressResponse =
		// addressByEmployeeId.getBody();

		// AddressResponse addressResponse = callingAddressServiceUsingRESTTemplate(id);

		AddressResponse addressResponse = callingAddressServiceUsingRESTTemplateWithLoadBalancedWay(id);
		employeeResponse.setAddressResponse(addressResponse);

		return employeeResponse;

	}

	private AddressResponse AddressResponseFromWebClient(int id) {
		return webClient.get().uri("/address/" + id).retrieve().bodyToMono(AddressResponse.class).block();
	}

	/*
	 * This is not load Balanced Way all request goes to 0 index instance
	 */
	/*
	 * private AddressResponse callingAddressServiceUsingRESTTemplate(int id) {
	 * List<ServiceInstance> instances =
	 * discoveryClient.getInstances("ADDRESSSERVICE"); ServiceInstance
	 * serviceInstance = instances.get(0); String uri =
	 * serviceInstance.getUri().toString(); String contextPath =
	 * serviceInstance.getMetadata().get("context-path"); System.out.println("Uri:"
	 * + uri + contextPath); return restTemplate.getForObject(uri + contextPath +
	 * "/address/{id}", AddressResponse.class, id); }
	 */
	private AddressResponse callingAddressServiceUsingRESTTemplateWithLoadBalancedWay(int id) {
		
		ServiceInstance instances = loadBalancer.choose("addressservice");
		String uri = instances.getUri().toString();
		String contextPath = instances.getMetadata().get("context-path");
		System.out.println("Uri:" + uri + contextPath);
		return restTemplate.getForObject("http://addressservice/"+ contextPath + "/address/{id}", AddressResponse.class, id);
	}

}
