package com.dnb.customerservice.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
//	@GenericGenerator(name = "customer_seq", type = CustomIdGenerator.class, parameters = {
//			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "50"),
//			@Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "CUST_"),
//			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private int customerId;
	
	private String customerName;
	
	private String customerContactNumber;
	
	private String customerAddress;
	
	private String customerPAN;
	
	private String customerUUID;

}
