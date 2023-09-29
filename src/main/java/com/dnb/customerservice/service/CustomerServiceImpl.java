package com.dnb.customerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.customerservice.dto.Customer;
import com.dnb.customerservice.exceptions.IdNotFoundException;
import com.dnb.customerservice.repository.CustomerRepository;

@Service("customerServiceImpl")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) throws IdNotFoundException {
		return customerRepository.save(customer);
	}

	@Override
	public Optional<Customer> getCustomerById(int customerId) {
		return customerRepository.findById(customerId);
	}

	@Override
	public boolean deleteCustomerById(int customerId) throws IdNotFoundException {
		boolean isExists = customerRepository.existsById(customerId);
		if (!isExists) {
			throw new IdNotFoundException("Id Not Found..");
		}
		customerRepository.deleteById(customerId);

		if (customerRepository.existsById(customerId))
			return false;
		else
			return true;
	}

	@Override
	public Iterable<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public boolean checkCustomerExistsById(int customerId) {
		return customerRepository.existsById(customerId);
	}

	@Override
	public List<Customer> getCustomerByCustomerContactNumber(String contactNumber) {
		return customerRepository.findByCustomerContactNumber(contactNumber);
	}

}
