package com.myapp.crud.appgen.address;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.myapp.crud.appgen.codeGroups.CodeGroups;
import com.myapp.crud.appgen.endUser.EndUser;

public interface AddressDAO extends CrudRepository<Address, Long>{
	public Page findAll(Pageable pageable);
	public List<Address> findByendUser(EndUser endUser);
	public List<Address> findByname(String itemCode);
	public List<Address> findByaddress1(String address1);
	public List<Address> findByaddress2(String address2);
	public List<Address> findBycity(String city);
	public List<Address> findBystate(String state);
	public List<Address> findBycountry(String country);
	public List<Address> findByzipCode(String zipCode);
	public List<Address> findBybillTo(String billTo);
	public List<Address> findByshipTo(String shipTo);

	@Query("select distinct c.code from com.myapp.crud.appgen.codeGroups.CodeGroups c")
	public List<CodeGroups> findByDistinctCode();
}


