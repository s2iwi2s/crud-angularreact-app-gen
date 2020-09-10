package com.myapp.crud.appgen.myCase;

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
public class MyCaseResponse extends Response{
	private MyCase myCase;
	private Page myCaseList;
	
	MyCaseListService listService;
	public MyCaseResponse(ResponseStatus responseStatus) {
		super(responseStatus);
	}
	
	public MyCaseResponse(ResponseStatus responseStatus, MyCaseListService listService) {
		super(responseStatus);
		this.listService = listService;
	}
/*
	private List<CodeGroups> statusList = new ArrayList<CodeGroups>();
	private List<CodeGroups> caseType1List = new ArrayList<CodeGroups>();
	private List<CodeGroups> caseType2List = new ArrayList<CodeGroups>();
	private List<CodeGroups> caseType3List = new ArrayList<CodeGroups>();
	private List<CodeGroups> statusCodeList = new ArrayList<CodeGroups>();

*/
}


