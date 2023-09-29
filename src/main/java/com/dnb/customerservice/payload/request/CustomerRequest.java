package com.dnb.customerservice.payload.request;

import lombok.Data;

@Data
public class CustomerRequest {

	private Integer customerId;
	private String customerName;
	private String customerContactNumber;
	private String customerAddress;
	private String customerPAN;
	private String customerUUID;
	
}
