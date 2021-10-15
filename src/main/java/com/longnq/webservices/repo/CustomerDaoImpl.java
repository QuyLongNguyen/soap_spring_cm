package com.longnq.webservices.repo;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.longnq.customers.Customer;

@Component
public class CustomerDaoImpl implements CustomerDao{
	
	@Autowired
	private EntityManager entityManager;
			
	@Override
	@Transactional
	public List<Customer> getCustomers(){
		
		Session session = entityManager.unwrap(Session.class);
		Query<Customer> query = session.createQuery("from Customer",Customer.class);
		List<Customer> customers = query.getResultList();
		return customers;
	}
	
	@Override
	@Transactional
	public Customer getCustomer(BigInteger id) {
		
		System.out.println("in customer");
		Session session = entityManager.unwrap(Session.class);
		Customer customer = session.get(Customer.class,id);
		
		return customer;
	}
}
