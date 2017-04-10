package com.wumugulu;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wumugulu.jdbc.model.Customer;
import com.wumugulu.jdbc.repository.CustomerRepository;
import com.wumugulu.jdbc.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter10ApplicationTests {

	@Resource
	private CustomerRepository customerRepository;

	@Test
	public void testCreate() {
		Customer customer = new Customer();
		customer.setCid(101);
		customer.setCustomerName("王自健 4");
		customer.setCustomerLevel("VIP-P 4");
		customer.setCustomerSource("东方卫视 4");
		customer.setCustomerPhone("020-88009965");
		customer.setCustomerMobile("13301661234");
		
		Integer result = customerRepository.create(customer);
		
		System.out.println(result);
		System.out.println(customer.toString());
	}

	@Resource
	CustomerService customerService;
	
	@Test
	public void testTransaction() {
		
		Integer result = customerService.delete2(3);
		
		System.out.println(result);
	}

}
