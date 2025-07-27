package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.DTO.OrganisationDetail;

@FeignClient(name = "Orgshekhar", path = "/orgNew")
public interface OrganisationFeignClient {

	@GetMapping("/org-details")
	public ResponseEntity<OrganisationDetail> getAllOrganisationDetails();
}
