package com.myapp.crud.appgen.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

public interface ProductDAO extends CrudRepository<Product, Long>{
	public Page findAll(Pageable pageable);
	public List<Product> findByitemCode(String itemCode);
	public List<Product> findBydescription(String description);
	public List<Product> findByprice(String price);
	public List<Product> findByquantity(String quantity);

	@Query("select distinct c.code from com.myapp.crud.appgen.codeGroups.CodeGroups c")
	public List<CodeGroups> findByDistinctCode();
}


