package com.myapp.crud.appgen.address;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.myapp.crud.appgen.codeGroups.CodeGroups;
import com.myapp.crud.appgen.codeGroups.CodeGroupsService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AddressListService {
	@Autowired
	private CodeGroupsService codeGroupsService;


	
	public AddressListService(CodeGroupsService codeGroupsService) {

	}
}


