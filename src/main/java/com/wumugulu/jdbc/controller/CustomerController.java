package com.wumugulu.jdbc.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wumugulu.jdbc.model.Customer;
import com.wumugulu.jdbc.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	@Resource
	private CustomerService customerService;

	@GetMapping("/getAll")
	@ResponseBody
	public ResponseEntity<Object> getAll(
			@RequestParam(value="n",defaultValue="1")Integer pageNum, 
			@RequestParam(value="s",defaultValue="30")Integer pageSize){
		List<Map<String, Object>> mapList = customerService.getAll(pageNum, pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(mapList);
	}

	@GetMapping("/getCount")
	@ResponseBody
	public ResponseEntity<Object> getCount(){
		Integer result = customerService.getCount();
		return ResponseEntity.status(HttpStatus.OK).body("Count: "+result);
	}

	@GetMapping("")
	@ResponseBody
	public ResponseEntity<Object> findAll(
			@RequestParam(value="n",defaultValue="1")Integer pageNum, 
			@RequestParam(value="s",defaultValue="30")Integer pageSize){
		List<Customer> objectList = customerService.findAll(pageNum, pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(objectList);
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Object> find(@PathVariable("id")Integer id){
		Customer customer = customerService.find(id);
		return ResponseEntity.status(HttpStatus.OK).body(customer);
	}
	
	@PostMapping("")
	@ResponseBody
	public ResponseEntity<Object> create(Customer customer){
		Integer result = customerService.create(customer);
		return ResponseEntity.status(HttpStatus.OK).body("Inserted: "+result);
	}
	
	@PostMapping("/create")
	@ResponseBody
	public ResponseEntity<Object> create2(Customer customer){
		System.out.println(customer.toString());
		Integer result = customerService.create2(customer);
		System.out.println(customer.toString());
		return ResponseEntity.status(HttpStatus.OK).body("Inserted: "+result);
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Object> update(@PathVariable("id")Integer id, Customer customer){
		System.out.println(customer.toString());
		Integer result = customerService.update(id, customer);
		return ResponseEntity.status(HttpStatus.OK).body("Updated: "+result);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Object> delete(@PathVariable("id")Integer id){
		Integer result = customerService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted: "+result);
	}
	
	
	
	@GetMapping("/conn")
	public ResponseEntity<Object> connectionTask(){
		String result = "connection success";
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@GetMapping("/session")
	public ResponseEntity<Object> sessionTest(HttpServletRequest request){
		request.getSession().setAttribute("myid", "hello springboot");//new Integer(199));
		String result = "session success";
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
