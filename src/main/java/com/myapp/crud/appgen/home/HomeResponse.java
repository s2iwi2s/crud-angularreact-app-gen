package com.myapp.crud.appgen.home;

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
public class HomeResponse extends Response{
	private Home home;
	private Page homeList;
	
	public HomeResponse(ResponseStatus responseStatus) {
		super(responseStatus);
	}

}


