package com.myapp.crud.appgen.partItem;

import java.util.List;

import org.springframework.data.domain.Page;

import com.myapp.crud.appgen.Response;
import com.myapp.crud.appgen.ResponseStatus;
import com.myapp.crud.appgen.codeGroups.CodeGroups;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PartItemResponse extends Response{
	private PartItem partItem;
	private List<PartItem> partItemsSearch;
	private Page partItemList;
	
	public PartItemResponse(ResponseStatus responseStatus) {
		super(responseStatus);
	}

}


