package com.myapp.crud.appgen.endUser;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

public interface EndUserDAO extends CrudRepository<EndUser, Long>{
	public Page findAll(Pageable pageable);
	public List<EndUser> findByfirstName(String firstName);
	public List<EndUser> findBylastName(String lastName);
	public List<EndUser> findByaddress(String address);

	@Query("select distinct c.code from com.myapp.crud.appgen.codeGroups.CodeGroups c")
	public List<CodeGroups> findByDistinctCode();
}


