package com.myapp.crud.appgen.endUser;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

@Service
public class EndUserService {
	private EndUserDAO endUserDAO;
	private PasswordEncoder bcryptEncoder;
	
	

	@Autowired
	public EndUserService(EndUserDAO endUserDAO, PasswordEncoder bcryptEncoder) {
		this.endUserDAO = endUserDAO;
		this.bcryptEncoder = bcryptEncoder;
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

	public EndUser save(EndUser endUser) throws UsernameNotFoundException{
		if(endUser.getPassword() != null && endUser.getPassword().length() > 0) {
			endUser.setPassword(bcryptEncoder.encode(endUser.getPassword()));
		} else {
			Optional<EndUser> endUserTemp = endUserDAO.findById(endUser.getId());
			if(!endUserTemp.isPresent()) {
				throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", endUser.getUserName()));
			}
			endUser.setPassword(endUserTemp.get().getPassword());
		}
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

	public EndUser findByUserName(String address) {
		return endUserDAO.findByUserName(address);
	}
}


