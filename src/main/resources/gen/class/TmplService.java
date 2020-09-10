package com.myapp.crud.appgen.XYclassYX;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

@Service
public class XYCLASSYXService {
	private XYCLASSYXDAO XYclassYXDAO;

	public XYCLASSYXService() {
	}

	@Autowired
	public XYCLASSYXService(XYCLASSYXDAO XYclassYXDAO) {
		this.XYclassYXDAO = XYclassYXDAO;
	}

	public Page findByAll(Pageable pageable) {
		return XYclassYXDAO.findAll(pageable);
//		return XYclassYXDAO.findAll(PageRequest.of(page, size));
	}
	
	public XYCLASSYX findById(Long id) {
		if(id != null && id.longValue() == -1) {
			return new XYCLASSYX();
		}
		return XYclassYXDAO.findById(id).get();
	}

	public XYCLASSYX save(XYCLASSYX XYclassYX) {
		return XYclassYXDAO.save(XYclassYX);
	}

	public void deleteById(Long id) {
		XYclassYXDAO.deleteById(id);
	}
	public List<CodeGroups> findByDistinctCode(){
		return XYclassYXDAO.findByDistinctCode();
	}
XYdao-service-tmplYX
}
