package com.myapp.crud.appgen.endUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

@Service
public class EndUserService {
	private EndUserDAO endUserDAO;

	public EndUserService() {
	}

	@Autowired
	public EndUserService(EndUserDAO endUserDAO) {
		this.endUserDAO = endUserDAO;
	}

	public Page findByAll(Pageable pageable) {
		return endUserDAO.findAll(pageable);
//		return endUserDAO.findAll(PageRequest.of(page, size));
	}
	
	public EndUser findById(Long id) {
		if(id != null && id.longValue() == -1) {
			return new EndUser();
		}
		return endUserDAO.findById(id).get();
	}

	public EndUser save(EndUser endUser) {
		return endUserDAO.save(endUser);
	}

	public void deleteById(Long id) {
		endUserDAO.deleteById(id);
	}
	public List<CodeGroups> findByDistinctCode(){
		return endUserDAO.findByDistinctCode();
	}
	public List<EndUser> findByfirstName(String firstName) {
		return endUserDAO.findByfirstName(firstName);
	}
	public List<EndUser> findBylastName(String lastName) {
		return endUserDAO.findBylastName(lastName);
	}
	public List<EndUser> findByaddress(String address) {
		return endUserDAO.findByaddress(address);
	}

}


