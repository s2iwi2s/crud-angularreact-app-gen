package com.myapp.crud.appgen.codeGroups;

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
public class CodeGroupsResponse extends Response{
	private CodeGroups codeGroups;
	private Page codeGroupsList;
	
	public CodeGroupsResponse(ResponseStatus responseStatus) {
		super(responseStatus);
	}
	private List<String> codeList = new ArrayList<String>();

}


