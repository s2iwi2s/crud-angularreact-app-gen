package com.myapp.crud.appgen.address;

import java.util.ArrayList;
import java.util.List;

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
import com.myapp.crud.appgen.Constants.ADDRESS_SEARCH_TYPE;
import com.myapp.crud.appgen.ResponseStatus;
import com.myapp.crud.appgen.codeGroups.CodeGroupsService;
import com.myapp.crud.appgen.endUser.EndUser;
import com.myapp.crud.appgen.endUser.EndUserService;

@RestController()
@RequestMapping(path = "/api")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private EndUserService endUserService;

	@Autowired
	private CodeGroupsService codeGroupsService;
	
	private AddressListService getAddressListService () {
		return new AddressListService(codeGroupsService);
	}

	public AddressController() {
	}

	@GetMapping("/addresss")
	//@CrossOrigin(origins = Constants.CLIENT_URL)
	public AddressResponse list(@PageableDefault(page = Constants.DEFAULT_PAGE_NUMBER, size = Constants.DEFAULT_PAGE_SIZE)
//		  @SortDefault.SortDefaults({
//		  @SortDefault(sort = "dateRecorded", direction = Sort.Direction.DESC),
//		  @SortDefault(sort = "encounterId", direction = Sort.Direction.ASC)
//		})
		Pageable pageable) {
		System.out.println("\n*** ENTERED ==>/addressList");
		ResponseStatus status = new ResponseStatus();
		AddressResponse res = new AddressResponse(status);

		Page addressList = null;
		try {
			addressList = addressService.findByAll(pageable);
			status.setMessage("SUCCESS!");

//			jInfo(addressList);
		} catch (Exception e) {
			status.setException(e);
			e.printStackTrace();
		}
		res.setAddressList(addressList);
		System.out.println("END ==>/addressList");
		return res;
	}

	@DeleteMapping("/addresss/{id}")
	//@CrossOrigin(origins = Constants.CLIENT_URL)
	public AddressResponse delete(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/address/" + id);

		ResponseStatus status = new ResponseStatus();
		AddressResponse res = new AddressResponse(status);
		try {
			addressService.deleteById(id);
			status.setMessage("SUCCESS!");
//			jInfo(address);
		} catch (Exception e) {
			e.printStackTrace();

			status.setException(e);
		}
		System.out.println("END ==>/address/delete/" + id);
		return res;
	}

	@GetMapping("/addresss/{id}")
	//@CrossOrigin(origins = Constants.CLIENT_URL)
	public AddressResponse get(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/address/" + id);
		Address address = null;
		ResponseStatus status = new ResponseStatus();
		AddressResponse res = new AddressResponse(status, getAddressListService());
		try {
			address = addressService.findById(id);
			status.setMessage("SUCCESS!");
//			jInfo(address);
		} catch (Exception e) {
			address = new Address();
			e.printStackTrace();

			status.setException(e);
		}

		res.setAddress(address);
		System.out.println("END ==>/address/" + id);
		return res;
	}
	@GetMapping("/addresss/endUser/{endUserId}")
	//@CrossOrigin(origins = Constants.CLIENT_URL)
	public AddressResponse getByEndUser(@PathVariable("endUserId") Long endUserId) {
		System.out.println("\n*** ENTERED ==>/address/endUser/" + endUserId);
		Address address = null;
		ResponseStatus status = new ResponseStatus();
		AddressResponse res = new AddressResponse(status, getAddressListService());
		try {
			address = new Address();
			EndUser endUser = endUserService.findById(endUserId);
			address.setEndUser(endUser);
			status.setMessage("SUCCESS!");
//			jInfo(address);
		} catch (Exception e) {
			e.printStackTrace();

			status.setException(e);
		}

		res.setAddress(address);
		System.out.println("END ==>/address/endUser/" + endUserId);
		return res;
	}
	
	@GetMapping("/addresss/by/{searchType}/{id}")
	//@CrossOrigin(origins = Constants.CLIENT_URL)
	public AddressResponse getByEndUser(@PathVariable("searchType") String searchType, @PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/address/"+searchType+"/" + id);
		List<Address> list = new ArrayList<Address>();
		ResponseStatus status = new ResponseStatus();
		AddressResponse res = new AddressResponse(status, getAddressListService());
		
		try {
			if(ADDRESS_SEARCH_TYPE.END_USER.toString().equalsIgnoreCase(searchType)) {
				System.out.println("ADDRESS_SEARCH_TYPE=" + ADDRESS_SEARCH_TYPE.END_USER);
				list = addressService.findByendUser(id);
			}
			status.setMessage("SUCCESS!");
//			jInfo(address);
		} catch (Exception e) {
			e.printStackTrace();
			status.setException(e);
		}
		res.setList(list);

		System.out.println("END ==>/address/"+searchType+"/" + id);
		return res;
	}

	@PostMapping(path = "/addresss", consumes = { MediaType.APPLICATION_JSON_VALUE })
	//@CrossOrigin(origins = Constants.CLIENT_URL)
	public Address save(@RequestBody Address address) {
		System.out.println("\n*** ENTERED ==>address/save");
		ResponseStatus status = new ResponseStatus();
		AddressResponse res = new AddressResponse(status, getAddressListService());
		try {
//			System.out.println("#######################");
//			jInfo(address);
//			System.out.println("#######################");
			addressService.save(address);
			status.setMessage("SUCCESS!");
//			System.out.println("=======================");
//			jInfo(address);
//			System.out.println("=======================");
		} catch (Exception e) {
			address = new Address();
			status.setException(e);
			e.printStackTrace();
		}
		res.setAddress(address);
		System.out.println("END ==>address/save");
		return address;
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


