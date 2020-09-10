package com.myapp.crud.appgen.product;

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
public class ProductResponse extends Response{
	private Product product;
	private Page productList;
	
	ProductListService listService;
	public ProductResponse(ResponseStatus responseStatus) {
		super(responseStatus);
	}
	
	public ProductResponse(ResponseStatus responseStatus, ProductListService listService) {
		super(responseStatus);
		this.listService = listService;
	}
/*

*/
}


