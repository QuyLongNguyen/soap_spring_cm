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
		
		Session session = entityManager.unwrap(Session.class);
		Customer customer = session.get(Customer.class,id);

		return customer;
	}

	@Override
	@Transactional
	public boolean addCustomer(Customer customer) {
		
		try {
			Session session = entityManager.unwrap(Session.class);
			customer.setId(BigInteger.valueOf(0));
			session.save(customer);
		}catch (Exception e) {
			return false;
		}
		
		return true;
	}

	@Override
	@Transactional
	public boolean updateCustomer(Customer customer) {
		
		Session session = entityManager.unwrap(Session.class);
		try {
			session.update(customer);
		}catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public boolean deleteCustomer(BigInteger id) {
		
		Session session = entityManager.unwrap(Session.class);
		try {
			Customer customer = getCustomer(id);
			if(customer == null) {
				return false;
			}
			session.delete(customer);
			
		}catch (Exception e) {
			return false;
		}
		
		return true;
	}
}
