package com.myapp.crud.appgen.codeGroups;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

public interface CodeGroupsDAO extends CrudRepository<CodeGroups, Long>{
	public Page findAll(Pageable pageable);
	public List<CodeGroups> findByCode(String code);
	public List<CodeGroups> findByValue(String value);
	public List<CodeGroups> findByDescription(String description);
	public List<CodeGroups> findByBool(String bool);
	public List<CodeGroups> findByNum(String num);

	@Query("select distinct c.code from com.myapp.crud.appgen.codeGroups.CodeGroups c")
	public List<String> findByDistinctCode();
}


