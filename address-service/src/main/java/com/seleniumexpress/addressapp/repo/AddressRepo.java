package com.seleniumexpress.addressapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seleniumexpress.addressapp.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

	@Query(nativeQuery = true, value = "SELECT a.id,a.lane1,a.lane2,a.state,a.zip FROM seleniumexpress_microservice.employee as e join seleniumexpress_microservice.address as a where e.id =:employeeId")
	Address findAddressByEmployeeId(@Param("employeeId") int employeeId);
}
