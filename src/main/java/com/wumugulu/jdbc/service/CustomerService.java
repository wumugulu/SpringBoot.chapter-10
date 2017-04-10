package com.wumugulu.jdbc.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wumugulu.jdbc.model.Customer;
import com.wumugulu.jdbc.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Resource
	private CustomerRepository customerRepository;

	public List<Map<String, Object>> getAll(Integer pageNum, Integer pageSize){
		Integer offset = (pageNum-1)*pageSize;
		Integer rows = pageSize;
		return customerRepository.getAll(offset, rows); 
	}

	public Integer getCount(){
		return customerRepository.getCount(); 
	}

	public List<Customer> findAll(Integer pageNum, Integer pageSize){
		Integer offset = (pageNum-1)*pageSize;
		Integer rows = pageSize;
		return customerRepository.findAll(offset, rows); 
	}

	public Customer find(Integer id){
		return customerRepository.find(id); 
	}

	public Integer create(Customer customer){
		return customerRepository.create(customer); 
	}

	public Integer create2(Customer customer){
		return customerRepository.create2(customer); 
	}


	public Integer update(Integer id, Customer customer){
		return customerRepository.update(id, customer); 
	}

	public Integer delete(Integer id){
		return customerRepository.delete(id); 
	}

	@Transactional
	public Integer delete2(Integer id){
		customerRepository.delete(105); 
		System.out.println("6/id = " + (6/id));
		customerRepository.delete(106);
		return 0;
	}

}
