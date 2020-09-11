package com.myapp.crud.appgen.endUser;

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
public class EndUserResponse extends Response{
	private EndUser endUser;
	private Page endUserList;
	
	EndUserListService listService;
	public EndUserResponse(ResponseStatus responseStatus) {
		super(responseStatus);
	}
	
	public EndUserResponse(ResponseStatus responseStatus, EndUserListService listService) {
		super(responseStatus);
		this.listService = listService;
	}
/*

*/
}


