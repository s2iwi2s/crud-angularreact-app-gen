package com.myapp.crud.appgen.customer;

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
public class CustomerResponse extends Response{
	private Customer customer;
	private Page customerList;
	
	CustomerListService listService;
	public CustomerResponse(ResponseStatus responseStatus) {
		super(responseStatus);
	}
	
	public CustomerResponse(ResponseStatus responseStatus, CustomerListService listService) {
		super(responseStatus);
		this.listService = listService;
	}
/*

*/
}


