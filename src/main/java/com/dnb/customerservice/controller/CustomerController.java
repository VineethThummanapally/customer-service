package com.dnb.customerservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnb.customerservice.dto.Customer;
import com.dnb.customerservice.exceptions.IdNotFoundException;
import com.dnb.customerservice.payload.request.CustomerRequest;
import com.dnb.customerservice.service.CustomerService;
import com.dnb.customerservice.utils.RequestToEntityMapper;

import jakarta.validation.Valid;

//@CrossOrigin("*")
@RestController
@RequestMapping("/customer")
public class CustomerController {

	// insert/ create Customer : post : @PostMapping
	@Autowired
	private CustomerService customerService;

	@Autowired
	private RequestToEntityMapper mapper;

	@DeleteMapping("/{customerId}")
	public ResponseEntity<?> deletecustomerById(@PathVariable int customerId) throws IdNotFoundException {

		if (customerService.checkCustomerExistsById(customerId)) {
			customerService.deleteCustomerById(customerId);
			return ResponseEntity.noContent().build();
		} else {
			throw new IdNotFoundException("Id Not Found");
		}
	}

	// path variable
	@GetMapping("/{customerId}") // it should help us to get the specific customer details
	public ResponseEntity<?> getCustomerById(@PathVariable("customerId") int customerId) throws IdNotFoundException {

		Optional<Customer> requestedCustomer = customerService.getCustomerById(customerId);
		if (requestedCustomer.isPresent())
			return ResponseEntity.ok(requestedCustomer.get());
		else {
			throw new IdNotFoundException("Requested Id Info Not found");
		}
	}

	@PostMapping("/create") // combination of @RequestMapping + PostMethod => spring 4.3.
	public ResponseEntity<?> creatCustomer(@Valid @RequestBody CustomerRequest customerRequest) {

		Customer customer2 = mapper.getCustomerEntityObject(customerRequest);
		
		try {
			Customer createdCustomer = customerService.createCustomer(customer2);
			return new ResponseEntity<Customer>(createdCustomer, HttpStatus.CREATED);
		} catch (IdNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/allCustomers/{customerContactNumber:^[0-9]{10}$}")
	public ResponseEntity<?> getCustomerByContactNumber(@PathVariable("customerContactNumber") String contactNumber)
			throws IdNotFoundException {
		List<Customer> ContactNumberedCustomer = customerService.getCustomerByCustomerContactNumber(contactNumber);

		if (ContactNumberedCustomer.size() != 0)
			return ResponseEntity.ok(ContactNumberedCustomer);
		else {
			throw new IdNotFoundException("Requested contact number Info Not found");
		}
	}

	@GetMapping("/allCustomers")
	public ResponseEntity<?> getAllCustomers() throws IdNotFoundException {
		List<Customer> allCustomers = (List<Customer>) customerService.getAllCustomers();

		if (allCustomers.size() != 0)
			return ResponseEntity.ok(allCustomers);
		else {
			throw new IdNotFoundException("No Customers Found ");
		}
	}
}
