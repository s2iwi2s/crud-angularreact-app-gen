package com.myapp.crud.appgen.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

@Service
public class CustomerService {
	private CustomerDAO customerDAO;

	public CustomerService() {
	}

	@Autowired
	public CustomerService(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	public Page findByAll(Pageable pageable) {
		return customerDAO.findAll(pageable);
//		return customerDAO.findAll(PageRequest.of(page, size));
	}
	
	public Customer findById(Long id) {
		if(id != null && id.longValue() == -1) {
			return new Customer();
		}
		return customerDAO.findById(id).get();
	}

	public Customer save(Customer customer) {
		return customerDAO.save(customer);
	}

	public void deleteById(Long id) {
		customerDAO.deleteById(id);
	}
	public List<CodeGroups> findByDistinctCode(){
		return customerDAO.findByDistinctCode();
	}
	public List<Customer> findByfirstName(String firstName) {
		return customerDAO.findByfirstName(firstName);
	}
	public List<Customer> findBylastName(String lastName) {
		return customerDAO.findBylastName(lastName);
	}
	public List<Customer> findByaddress1(String address1) {
		return customerDAO.findByaddress1(address1);
	}
	public List<Customer> findByaddress2(String address2) {
		return customerDAO.findByaddress2(address2);
	}
	public List<Customer> findBycity(String city) {
		return customerDAO.findBycity(city);
	}
	public List<Customer> findBystate(String state) {
		return customerDAO.findBystate(state);
	}

}


