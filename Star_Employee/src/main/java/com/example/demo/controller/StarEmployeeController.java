package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StarEmployeeDTO;

@RestController
@RefreshScope
public class StarEmployeeController {

	private static Logger logger = LoggerFactory.getLogger(StarEmployeeController.class);
	@Autowired
	private StarEmployeeDTO dto;
	
	/*
	 * @Value("${spring.value.key}") private String keyValue;
	 */

	@GetMapping("/emp")
	public StarEmployeeDTO getStarEmployee() {
		//logger.info("key:{}",keyValue);
		StarEmployeeDTO employeeDTO = new StarEmployeeDTO();
		employeeDTO.setDepartment(dto.getDepartment());
		employeeDTO.setStarEmployeeOfTheMonth(dto.getStarEmployeeOfTheMonth());
		return employeeDTO;
	}
}
