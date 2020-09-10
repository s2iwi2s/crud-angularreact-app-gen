package com.myapp.crud.appgen.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.myapp.crud.appgen.Constants;
import com.myapp.crud.appgen.ResponseStatus;
import com.myapp.crud.appgen.codeGroups.CodeGroupsService;

@RestController()
@RequestMapping(path = "/api")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CodeGroupsService codeGroupsService;
	
	private ProductListService getProductListService () {
		return new ProductListService(codeGroupsService);
	}

	public ProductController() {
	}

	@GetMapping("/products")
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public ProductResponse list(@PageableDefault(page = Constants.DEFAULT_PAGE_NUMBER, size = Constants.DEFAULT_PAGE_SIZE)
//		  @SortDefault.SortDefaults({
//		  @SortDefault(sort = "dateRecorded", direction = Sort.Direction.DESC),
//		  @SortDefault(sort = "encounterId", direction = Sort.Direction.ASC)
//		})
		Pageable pageable) {
		System.out.println("\n*** ENTERED ==>/productList");
		ResponseStatus status = new ResponseStatus();
		ProductResponse res = new ProductResponse(status);

		Page productList = null;
		try {
			productList = productService.findByAll(pageable);
			status.setMessage("SUCCESS!");

//			jInfo(productList);
		} catch (Exception e) {
			status.setException(e);
			e.printStackTrace();
		}
		res.setProductList(productList);
		System.out.println("END ==>/productList");
		return res;
	}

	@DeleteMapping("/products/{id}")
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public ProductResponse delete(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/product/" + id);

		ResponseStatus status = new ResponseStatus();
		ProductResponse res = new ProductResponse(status);
		try {
			productService.deleteById(id);
			status.setMessage("SUCCESS!");
//			jInfo(product);
		} catch (Exception e) {
			e.printStackTrace();

			status.setException(e);
		}
		System.out.println("END ==>/product/delete/" + id);
		return res;
	}

	@GetMapping("/products/{id}")
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public ProductResponse get(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/product/" + id);
		Product product = null;
		ResponseStatus status = new ResponseStatus();
		ProductResponse res = new ProductResponse(status, getProductListService());
		try {
			product = productService.findById(id);
			status.setMessage("SUCCESS!");
//			jInfo(product);
		} catch (Exception e) {
			product = new Product();
			e.printStackTrace();

			status.setException(e);
		}

		res.setProduct(product);
		System.out.println("END ==>/product/" + id);
		return res;
	}

	@PostMapping(path = "/products", consumes = { MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public Product save(@RequestBody Product product) {
		System.out.println("\n*** ENTERED ==>product/save");
		ResponseStatus status = new ResponseStatus();
		ProductResponse res = new ProductResponse(status, getProductListService());
		try {
//			System.out.println("#######################");
//			jInfo(product);
//			System.out.println("#######################");
			productService.save(product);
			status.setMessage("SUCCESS!");
//			System.out.println("=======================");
//			jInfo(product);
//			System.out.println("=======================");
		} catch (Exception e) {
			product = new Product();
			status.setException(e);
			e.printStackTrace();
		}
		res.setProduct(product);
		System.out.println("END ==>product/save");
		return product;
	}

	private void jInfo(Object obj) {
		try {
			ObjectMapper jsonObjMap = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
			String json = jsonObjMap.writeValueAsString(obj);
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}


