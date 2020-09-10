package com.myapp.crud.appgen.customer;

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
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CodeGroupsService codeGroupsService;
	
	private CustomerListService getCustomerListService () {
		return new CustomerListService(codeGroupsService);
	}

	public CustomerController() {
	}

	@GetMapping("/customers")
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public CustomerResponse list(@PageableDefault(page = Constants.DEFAULT_PAGE_NUMBER, size = Constants.DEFAULT_PAGE_SIZE)
//		  @SortDefault.SortDefaults({
//		  @SortDefault(sort = "dateRecorded", direction = Sort.Direction.DESC),
//		  @SortDefault(sort = "encounterId", direction = Sort.Direction.ASC)
//		})
		Pageable pageable) {
		System.out.println("\n*** ENTERED ==>/customerList");
		ResponseStatus status = new ResponseStatus();
		CustomerResponse res = new CustomerResponse(status);

		Page customerList = null;
		try {
			customerList = customerService.findByAll(pageable);
			status.setMessage("SUCCESS!");

//			jInfo(customerList);
		} catch (Exception e) {
			status.setException(e);
			e.printStackTrace();
		}
		res.setCustomerList(customerList);
		System.out.println("END ==>/customerList");
		return res;
	}

	@DeleteMapping("/customers/{id}")
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public CustomerResponse delete(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/customer/" + id);

		ResponseStatus status = new ResponseStatus();
		CustomerResponse res = new CustomerResponse(status);
		try {
			customerService.deleteById(id);
			status.setMessage("SUCCESS!");
//			jInfo(customer);
		} catch (Exception e) {
			e.printStackTrace();

			status.setException(e);
		}
		System.out.println("END ==>/customer/delete/" + id);
		return res;
	}

	@GetMapping("/customers/{id}")
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public CustomerResponse get(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/customer/" + id);
		Customer customer = null;
		ResponseStatus status = new ResponseStatus();
		CustomerResponse res = new CustomerResponse(status, getCustomerListService());
		try {
			customer = customerService.findById(id);
			status.setMessage("SUCCESS!");
//			jInfo(customer);
		} catch (Exception e) {
			customer = new Customer();
			e.printStackTrace();

			status.setException(e);
		}

		res.setCustomer(customer);
		System.out.println("END ==>/customer/" + id);
		return res;
	}

	@PostMapping(path = "/customers", consumes = { MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public Customer save(@RequestBody Customer customer) {
		System.out.println("\n*** ENTERED ==>customer/save");
		ResponseStatus status = new ResponseStatus();
		CustomerResponse res = new CustomerResponse(status, getCustomerListService());
		try {
//			System.out.println("#######################");
//			jInfo(customer);
//			System.out.println("#######################");
			customerService.save(customer);
			status.setMessage("SUCCESS!");
//			System.out.println("=======================");
//			jInfo(customer);
//			System.out.println("=======================");
		} catch (Exception e) {
			customer = new Customer();
			status.setException(e);
			e.printStackTrace();
		}
		res.setCustomer(customer);
		System.out.println("END ==>customer/save");
		return customer;
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


