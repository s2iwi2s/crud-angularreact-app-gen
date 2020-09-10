package com.myapp.crud.appgen.myCase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

@Service
public class MyCaseService {
	private MyCaseDAO myCaseDAO;

	public MyCaseService() {
	}

	@Autowired
	public MyCaseService(MyCaseDAO myCaseDAO) {
		this.myCaseDAO = myCaseDAO;
	}

	public Page findByAll(Pageable pageable) {
		return myCaseDAO.findAll(pageable);
//		return myCaseDAO.findAll(PageRequest.of(page, size));
	}
	
	public MyCase findById(Long id) {
		if(id != null && id.longValue() == -1) {
			return new MyCase();
		}
		return myCaseDAO.findById(id).get();
	}

	public MyCase save(MyCase myCase) {
		return myCaseDAO.save(myCase);
	}

	public void deleteById(Long id) {
		myCaseDAO.deleteById(id);
	}
	public List<CodeGroups> findByDistinctCode(){
		return myCaseDAO.findByDistinctCode();
	}
	public List<MyCase> findByTitle(String title) {
		return myCaseDAO.findByTitle(title);
	}
	public List<MyCase> findByStatus(String status) {
		return myCaseDAO.findByStatus(status);
	}
	public List<MyCase> findByCaseType1(String caseType1) {
		return myCaseDAO.findByCaseType1(caseType1);
	}
	public List<MyCase> findByCaseType2(String caseType2) {
		return myCaseDAO.findByCaseType2(caseType2);
	}
	public List<MyCase> findByCaseType3(String caseType3) {
		return myCaseDAO.findByCaseType3(caseType3);
	}
	public List<MyCase> findByStatusCode(String statusCode) {
		return myCaseDAO.findByStatusCode(statusCode);
	}
	public List<MyCase> findByComments(String comments) {
		return myCaseDAO.findByComments(comments);
	}

}


