package com.myapp.crud.appgen.partItem;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

public interface PartItemDAO extends CrudRepository<PartItem, Long>{
	public Page findAll(Pageable pageable);
	public List<PartItem> findByNameContaining(String name);
	public List<PartItem> findByDescription(String description);
	public List<PartItem> findBySerialized(String serialized);

	@Query("select distinct c.code from com.myapp.crud.appgen.codeGroups.CodeGroups c")
	public List<CodeGroups> findByDistinctCode();
}


