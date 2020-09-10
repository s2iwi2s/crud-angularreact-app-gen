package com.myapp.crud.appgen.myCase;


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
public class MyCaseListService {
	@Autowired
	private CodeGroupsService codeGroupsService;

	private List<CodeGroups> statusList = null;
	private List<CodeGroups> caseType1List = null;
	private List<CodeGroups> caseType2List = null;
	private List<CodeGroups> caseType3List = null;
	private List<CodeGroups> statusCodeList = null;

	
	public MyCaseListService(CodeGroupsService codeGroupsService) {
		statusList = codeGroupsService.findByCode("STATUS");
		caseType1List = codeGroupsService.findByCode("CASE_TYPE_1");
		caseType2List = codeGroupsService.findByCode("CASE_TYPE_2");
		caseType3List = codeGroupsService.findByCode("CASE_TYPE_3");
		statusCodeList = codeGroupsService.findByCode("STATUS_CODE");

	}
}


