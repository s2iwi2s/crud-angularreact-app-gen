package com.myapp.crud.appgen.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

@Service
public class ProductService {
	private ProductDAO productDAO;

	public ProductService() {
	}

	@Autowired
	public ProductService(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public Page findByAll(Pageable pageable) {
		return productDAO.findAll(pageable);
//		return productDAO.findAll(PageRequest.of(page, size));
	}
	
	public Product findById(Long id) {
		if(id != null && id.longValue() == -1) {
			return new Product();
		}
		return productDAO.findById(id).get();
	}

	public Product save(Product product) {
		return productDAO.save(product);
	}

	public void deleteById(Long id) {
		productDAO.deleteById(id);
	}
	public List<CodeGroups> findByDistinctCode(){
		return productDAO.findByDistinctCode();
	}
	public List<Product> findByitemCode(String itemCode) {
		return productDAO.findByitemCode(itemCode);
	}
	public List<Product> findBydescription(String description) {
		return productDAO.findBydescription(description);
	}
	public List<Product> findBycategory(String category) {
		return productDAO.findBycategory(category);
	}
	public List<Product> findByprice(String price) {
		return productDAO.findByprice(price);
	}
	public List<Product> findByquantity(String quantity) {
		return productDAO.findByquantity(quantity);
	}

}


