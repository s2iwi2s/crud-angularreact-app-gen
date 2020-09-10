package com.myapp.crud.appgen.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

@Service
public class HomeService {
	private HomeDAO homeDAO;

	public HomeService() {
	}

	@Autowired
	public HomeService(HomeDAO homeDAO) {
		this.homeDAO = homeDAO;
	}

	public Page findByAll(Pageable pageable) {
		return homeDAO.findAll(pageable);
//		return homeDAO.findAll(PageRequest.of(page, size));
	}
	
	public Home findById(Long id) {
		if(id != null && id.longValue() == -1) {
			return new Home();
		}
		return homeDAO.findById(id).get();
	}

	public Home save(Home home) {
		return homeDAO.save(home);
	}

	public void deleteById(Long id) {
		homeDAO.deleteById(id);
	}
	public List<CodeGroups> findByDistinctCode(){
		return homeDAO.findByDistinctCode();
	}
	public List<Home> findByUrlStr(String urlStr) {
		return homeDAO.findByUrlStr(urlStr);
	}
	public List<Home> findByName(String name) {
		return homeDAO.findByName(name);
	}

}


