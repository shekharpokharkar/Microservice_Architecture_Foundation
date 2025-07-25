package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

	@Query(nativeQuery = true,

			value = "SELECT a.id,a.lane1, a.lane2, a.state,a.zip,a.employee_id FROM seleniumexpress_microservice.address a  WHERE a.employee_id = :employeeId")
	Address findAddressByEmployeeId(@Param("employeeId") int employeeId);
}
