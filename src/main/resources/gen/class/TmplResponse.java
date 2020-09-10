package com.myapp.crud.appgen.XYclassYX;

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
public class XYCLASSYXResponse extends Response{
	private XYCLASSYX XYclassYX;
	private Page XYclassYXList;
	
	XYCLASSYXListService listService;
	public XYCLASSYXResponse(ResponseStatus responseStatus) {
		super(responseStatus);
	}
	
	public XYCLASSYXResponse(ResponseStatus responseStatus, XYCLASSYXListService listService) {
		super(responseStatus);
		this.listService = listService;
	}
/*
XYresponse-fieldYX
*/
}
