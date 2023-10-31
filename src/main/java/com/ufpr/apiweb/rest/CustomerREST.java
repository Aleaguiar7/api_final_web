package com.ufpr.apiweb.rest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.apiweb.model.Customer;

@CrossOrigin
@RestController
public class CustomerREST {
	public static List<Customer> lista = new ArrayList<>();	
	
	@GetMapping("/customer")
	public List<Customer> obterCustomers(){
		return lista;
	}
	
	@GetMapping("customer/{id}")
	public Customer obterCustomers1(@PathVariable ("id") int id) {
		Customer c = lista.stream().filter(cust -> cust.getId() == id).findAny().orElse(null);
		return c; 
	}
	
	@PostMapping("/customer")
	public Customer inserirCustomer(@RequestBody Customer customer) {
		Customer c = lista.stream().max(Comparator.comparing(Customer::getId)).orElse(null);
		if(c == null)
			customer.setId(1);
		else
			customer.setId(c.getId()+1);
		lista.add(customer);
		return customer;
	}
	
	@PutMapping("customer/{id}")
	public Customer alterarCustomer(@PathVariable("id") int id, @RequestBody Customer customer){
		Customer c = lista.stream().filter(cust -> cust.getId() == id).findAny().orElse(null);
		if (c != null) {
			c.setNome(customer.getNome());
			c.setSobrenome(customer.getSobrenome());
		}
	return c;
	
}
	@DeleteMapping("/customer/{id}")
	public Customer removerCustomer(@PathVariable("id") int id) {
		Customer customer = lista.stream().filter(cust -> cust.getId() == id).findAny().orElse(null);
		if (customer != null)
			lista.removeIf(c -> c.getId() == id);
		return customer;
		
	}

	static {		
		lista.add(new Customer(1, "Joao", "Silva"));
		lista.add(new Customer(2, "Maria", "Santos"));
		lista.add(new Customer(3, "Juliana", "Silva"));		
	
}
	}
