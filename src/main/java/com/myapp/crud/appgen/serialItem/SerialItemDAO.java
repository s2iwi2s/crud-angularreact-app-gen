package com.myapp.crud.appgen.serialItem;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

public interface SerialItemDAO extends CrudRepository<SerialItem, Long>{
	public Page findAll(Pageable pageable);
	public List<SerialItem> findByName(String name);
	public List<SerialItem> findByDescription(String description);
	public List<SerialItem> findByPartItem(String partItem);
	public List<SerialItem> findByStatus(String status);

	@Query("select distinct c.code from com.myapp.crud.appgen.codeGroups.CodeGroups c")
	public List<CodeGroups> findByDistinctCode();
}


