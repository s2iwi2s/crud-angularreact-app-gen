package com.myapp.crud.appgen.customer;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

public interface CustomerDAO extends CrudRepository<Customer, Long>{
	public Page findAll(Pageable pageable);
	public List<Customer> findByfirstName(String firstName);
	public List<Customer> findBylastName(String lastName);
	public List<Customer> findByaddress1(String address1);
	public List<Customer> findByaddress2(String address2);
	public List<Customer> findBycity(String city);
	public List<Customer> findBystate(String state);

	@Query("select distinct c.code from com.myapp.crud.appgen.codeGroups.CodeGroups c")
	public List<CodeGroups> findByDistinctCode();
}


