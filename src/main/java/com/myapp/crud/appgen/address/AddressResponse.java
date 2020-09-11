package com.myapp.crud.appgen.address;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.myapp.crud.appgen.Response;
import com.myapp.crud.appgen.ResponseStatus;
import com.myapp.crud.appgen.codeGroups.CodeGroups;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse extends Response{
	private Address address;
	private Page addressList;

	private List<Address> list;
	
	AddressListService listService;
	public AddressResponse(ResponseStatus responseStatus) {
		super(responseStatus);
	}
	
	public AddressResponse(ResponseStatus responseStatus, AddressListService listService) {
		super(responseStatus);
		this.listService = listService;
	}
/*

*/
}


