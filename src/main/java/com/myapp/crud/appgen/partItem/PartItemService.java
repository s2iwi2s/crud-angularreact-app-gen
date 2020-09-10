package com.myapp.crud.appgen.partItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

@Service
public class PartItemService {
	private PartItemDAO partItemDAO;

	public PartItemService() {
	}

	@Autowired
	public PartItemService(PartItemDAO partItemDAO) {
		this.partItemDAO = partItemDAO;
	}

	public Page findByAll(Pageable pageable) {
		return partItemDAO.findAll(pageable);
//		return partItemDAO.findAll(PageRequest.of(page, size));
	}
	
	public PartItem findById(Long id) {
		if(id != null && id.longValue() == -1) {
			return new PartItem();
		}
		return partItemDAO.findById(id).get();
	}

	public PartItem save(PartItem partItem) {
		return partItemDAO.save(partItem);
	}

	public void deleteById(Long id) {
		partItemDAO.deleteById(id);
	}
	public List<CodeGroups> findByDistinctCode(){
		return partItemDAO.findByDistinctCode();
	}
	public List<PartItem> findByName(String name) {
		return partItemDAO.findByNameContaining(name);
	}
	public List<PartItem> findByDescription(String description) {
		return partItemDAO.findByDescription(description);
	}
	public List<PartItem> findBySerialized(String serialized) {
		return partItemDAO.findBySerialized(serialized);
	}

}


