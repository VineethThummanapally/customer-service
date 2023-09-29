package com.dnb.customerservice.service;


import java.util.List;
import java.util.Optional;

import com.dnb.customerservice.dto.Customer;
import com.dnb.customerservice.exceptions.IdNotFoundException;

public interface CustomerService {
	public Customer createCustomer(Customer customer) throws IdNotFoundException;

	public Optional<Customer> getCustomerById(int customerId);

	public boolean deleteCustomerById(int customerId) throws IdNotFoundException;

	public Iterable<Customer> getAllCustomers();
	
	public boolean checkCustomerExistsById(int accountId);

	public List<Customer> getCustomerByCustomerContactNumber(String contactNumber);
}
