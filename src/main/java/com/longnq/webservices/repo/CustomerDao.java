package com.longnq.webservices.repo;

import java.math.BigInteger;
import java.util.List;

import com.longnq.customers.Customer;

public interface CustomerDao {
	
	List<Customer> getCustomers();
	
	Customer getCustomer(BigInteger id);
}
