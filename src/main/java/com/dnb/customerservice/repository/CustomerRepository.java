package com.dnb.customerservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dnb.customerservice.dto.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	public List<Customer> findByCustomerContactNumber(String contactNumber);
}
