package com.myapp.crud.appgen.myCase;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

public interface MyCaseDAO extends CrudRepository<MyCase, Long>{
	public Page findAll(Pageable pageable);
	public List<MyCase> findByTitle(String title);
	public List<MyCase> findByStatus(String status);
	public List<MyCase> findByCaseType1(String caseType1);
	public List<MyCase> findByCaseType2(String caseType2);
	public List<MyCase> findByCaseType3(String caseType3);
	public List<MyCase> findByStatusCode(String statusCode);
	public List<MyCase> findByComments(String comments);

	@Query("select distinct c.code from com.myapp.crud.appgen.codeGroups.CodeGroups c")
	public List<CodeGroups> findByDistinctCode();
}


