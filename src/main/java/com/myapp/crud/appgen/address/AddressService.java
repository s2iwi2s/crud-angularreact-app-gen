package com.myapp.crud.appgen.address;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myapp.crud.appgen.Constants;
import com.myapp.crud.appgen.codeGroups.CodeGroups;
import com.myapp.crud.appgen.endUser.EndUser;
import com.myapp.crud.appgen.endUser.EndUserDAO;

@Service
public class AddressService {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private AddressDAO addressDAO;
	private EndUserDAO endUserDAO;

	public AddressService() {
	}

	@Autowired
	public AddressService(AddressDAO addressDAO, EndUserDAO endUserDAO) {
		this.addressDAO = addressDAO;
		this.endUserDAO = endUserDAO;
	}

	public Page findByAll(Pageable pageable) {
		return addressDAO.findAll(pageable);
//		return addressDAO.findAll(PageRequest.of(page, size));
	}
	
	public Address findById(Long id) {
		if(id != null && id.longValue() == -1) {
			return new Address();
		}
		return addressDAO.findById(id).get();
	}

	public Address save(Address address) {
		return addressDAO.save(address);
	}

	public void deleteById(Long id) {
		addressDAO.deleteById(id);
	}
	public List<CodeGroups> findByDistinctCode(){
		return addressDAO.findByDistinctCode();
	}
	public List<Address> findByendUser(Long endUserId) {
		Optional<EndUser> endUser = endUserDAO.findById(endUserId);
		return addressDAO.findByendUser(endUser.get());
	}
	public List<Address> findByname(String itemCode) {
		return addressDAO.findByname(itemCode);
	}
	public List<Address> findByaddress1(String address1) {
		return addressDAO.findByaddress1(address1);
	}
	public List<Address> findByaddress2(String address2) {
		return addressDAO.findByaddress2(address2);
	}
	public List<Address> findBycity(String city) {
		return addressDAO.findBycity(city);
	}
	public List<Address> findBystate(String state) {
		return addressDAO.findBystate(state);
	}
	public List<Address> findBycountry(String country) {
		return addressDAO.findBycountry(country);
	}
	public List<Address> findByzipCode(String zipCode) {
		return addressDAO.findByzipCode(zipCode);
	}
	public List<Address> findBybillTo(String billTo) {
		return addressDAO.findBybillTo(billTo);
	}
	public List<Address> findByshipTo(String shipTo) {
		return addressDAO.findByshipTo(shipTo);
	}

}


