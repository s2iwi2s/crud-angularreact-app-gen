package com.myapp.crud.appgen.codeGroups;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

@Service
public class CodeGroupsService {
	private CodeGroupsDAO codeGroupsDAO;

	public CodeGroupsService() {
	}

	@Autowired
	public CodeGroupsService(CodeGroupsDAO codeGroupsDAO) {
		this.codeGroupsDAO = codeGroupsDAO;
	}

	public Page findByAll(Pageable pageable) {
		return codeGroupsDAO.findAll(pageable);
//		return codeGroupsDAO.findAll(PageRequest.of(page, size));
	}
	
	public CodeGroups findById(Long id) {
		if(id != null && id.longValue() == -1) {
			return new CodeGroups();
		}
		return codeGroupsDAO.findById(id).get();
	}

	public CodeGroups save(CodeGroups codeGroups) {
		return codeGroupsDAO.save(codeGroups);
	}

	public void deleteById(Long id) {
		codeGroupsDAO.deleteById(id);
	}
	public List<String> findByDistinctCode(){
		return codeGroupsDAO.findByDistinctCode();
	}
	public List<CodeGroups> findByCode(String code) {
		return codeGroupsDAO.findByCode(code);
	}
	public List<CodeGroups> findByValue(String value) {
		return codeGroupsDAO.findByValue(value);
	}
	public List<CodeGroups> findByDescription(String description) {
		return codeGroupsDAO.findByDescription(description);
	}
	public List<CodeGroups> findByBool(String bool) {
		return codeGroupsDAO.findByBool(bool);
	}
	public List<CodeGroups> findByNum(String num) {
		return codeGroupsDAO.findByNum(num);
	}

}


