package com.myapp.crud.appgen.XYclassYX;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

public interface XYCLASSYXDAO extends CrudRepository<XYCLASSYX, Long>{
	public Page findAll(Pageable pageable);
XYdao-methodsYX
	@Query("select distinct c.code from com.myapp.crud.appgen.codeGroups.CodeGroups c")
	public List<CodeGroups> findByDistinctCode();
}
