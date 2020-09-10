package com.myapp.crud.appgen.serialItem;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

@Service
public class SerialItemService {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private SerialItemDAO serialItemDAO;

	public SerialItemService() {
	}

	@Autowired
	public SerialItemService(SerialItemDAO serialItemDAO) {
		this.serialItemDAO = serialItemDAO;
	}

	public Page findByAll(Pageable pageable) {
		return serialItemDAO.findAll(pageable);
//		return serialItemDAO.findAll(PageRequest.of(page, size));
	}
	
	public SerialItem findById(Long id) {
		if(id != null && id.longValue() == -1) {
			return new SerialItem();
		}
		return serialItemDAO.findById(id).get();
	}

	public SerialItem save(SerialItem serialItem) {
		log.info("serialItem.getPartItem=>" + serialItem.getPartItem());
		return serialItemDAO.save(serialItem);
	}

	public void deleteById(Long id) {
		serialItemDAO.deleteById(id);
	}
	public List<CodeGroups> findByDistinctCode(){
		return serialItemDAO.findByDistinctCode();
	}
	public List<SerialItem> findByName(String name) {
		return serialItemDAO.findByName(name);
	}
	public List<SerialItem> findByDescription(String description) {
		return serialItemDAO.findByDescription(description);
	}
	public List<SerialItem> findByPartItem(String partItem) {
		return serialItemDAO.findByPartItem(partItem);
	}
	public List<SerialItem> findByStatus(String status) {
		return serialItemDAO.findByStatus(status);
	}

}


