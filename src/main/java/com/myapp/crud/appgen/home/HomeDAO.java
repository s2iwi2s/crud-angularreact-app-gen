package com.myapp.crud.appgen.home;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

public interface HomeDAO extends CrudRepository<Home, Long>{
	public Page findAll(Pageable pageable);
	public List<Home> findByUrlStr(String urlStr);
	public List<Home> findByName(String name);

	@Query("select distinct c.code from com.myapp.crud.appgen.codeGroups.CodeGroups c")
	public List<CodeGroups> findByDistinctCode();
}


