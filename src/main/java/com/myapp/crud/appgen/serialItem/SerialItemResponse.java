package com.myapp.crud.appgen.serialItem;

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
public class SerialItemResponse extends Response{
	private SerialItem serialItem;
	private Page serialItemList;
	
	public SerialItemResponse(ResponseStatus responseStatus) {
		super(responseStatus);
	}

}


