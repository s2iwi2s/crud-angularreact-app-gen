package com.myapp.crud.appgen.serialItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.myapp.crud.appgen.Constants;
import com.myapp.crud.appgen.ResponseStatus;
import com.myapp.crud.appgen.codeGroups.CodeGroupsResponse;
import com.myapp.crud.appgen.codeGroups.CodeGroupsService;

@RestController()
@RequestMapping(path = "/api")
public class SerialItemController {

	@Autowired
	private SerialItemService serialItemService;

	@Autowired
	private CodeGroupsService codeGroupsService;

	public SerialItemController() {
	}

	@GetMapping("/serialItems")
	//@CrossOrigin(origins = Constants.CLIENT_URL)
	public SerialItemResponse list(@PageableDefault(page = Constants.DEFAULT_PAGE_NUMBER, size = Constants.DEFAULT_PAGE_SIZE)
//		  @SortDefault.SortDefaults({
//		  @SortDefault(sort = "dateRecorded", direction = Sort.Direction.DESC),
//		  @SortDefault(sort = "encounterId", direction = Sort.Direction.ASC)
//		})
		Pageable pageable) {
		System.out.println("\n*** ENTERED ==>/serialItemList");
		ResponseStatus status = new ResponseStatus();
		SerialItemResponse res = new SerialItemResponse(status);

		Page serialItemList = null;
		try {
			serialItemList = serialItemService.findByAll(pageable);
			status.setMessage("SUCCESS!");

//			jInfo(serialItemList);
		} catch (Exception e) {
			status.setException(e);
			e.printStackTrace();
		}
		res.setSerialItemList(serialItemList);
		System.out.println("END ==>/serialItemList");
		return res;
	}

	@DeleteMapping("/serialItems/{id}")
	//@CrossOrigin(origins = Constants.CLIENT_URL)
	public SerialItemResponse delete(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/serialItem/" + id);

		ResponseStatus status = new ResponseStatus();
		SerialItemResponse res = new SerialItemResponse(status);
		try {
			serialItemService.deleteById(id);
			status.setMessage("SUCCESS!");
//			jInfo(serialItem);
		} catch (Exception e) {
			e.printStackTrace();

			status.setException(e);
		}
		System.out.println("END ==>/serialItem/delete/" + id);
		return res;
	}

	@GetMapping("/serialItems/{id}")
	//@CrossOrigin(origins = Constants.CLIENT_URL)
	public SerialItemResponse get(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/serialItem/" + id);
		SerialItem serialItem = null;
		ResponseStatus status = new ResponseStatus();
		SerialItemResponse res = new SerialItemResponse(status);
		try {
			serialItem = serialItemService.findById(id);
			status.setMessage("SUCCESS!");
//			jInfo(serialItem);
		} catch (Exception e) {
			serialItem = new SerialItem();
			e.printStackTrace();

			status.setException(e);
		}

		res.setSerialItem(serialItem);
		this.setCodeGroupsList(res);
		System.out.println("END ==>/serialItem/" + id);
		return res;
	}

	private void setCodeGroupsList(SerialItemResponse res) {
//		res.setCodeList(codeGroupsService.findByDistinctCode());

//		res.setCaseType1List(codeGroupsService.findByCode("CASE_TYPE_1"));
//		res.setCaseType2List(codeGroupsService.findByCode("CASE_TYPE_2"));
//		res.setCaseType3List(codeGroupsService.findByCode("CASE_TYPE_3"));
//		res.setStatusList(codeGroupsService.findByCode("STATUS"));
//		res.setStatusCodeList(codeGroupsService.findByCode("STATUS_CODE"));
	}

	@PostMapping(path = "/serialItems", consumes = { MediaType.APPLICATION_JSON_VALUE })
	//@CrossOrigin(origins = Constants.CLIENT_URL)
	public SerialItem save(@RequestBody SerialItem serialItem) {
		System.out.println("\n*** ENTERED ==>serialItem/save");
		ResponseStatus status = new ResponseStatus();
		SerialItemResponse res = new SerialItemResponse(status);
		try {
//			System.out.println("#######################");
//			jInfo(serialItem);
//			System.out.println("#######################");
			serialItemService.save(serialItem);
			status.setMessage("SUCCESS!");
//			System.out.println("=======================");
//			jInfo(serialItem);
//			System.out.println("=======================");
		} catch (Exception e) {
			serialItem = new SerialItem();
			status.setException(e);
			e.printStackTrace();
		}
		res.setSerialItem(serialItem);
		System.out.println("END ==>serialItem/save");
		return serialItem;
	}

	private void jInfo(Object obj) {
		try {
			ObjectMapper jsonObjMap = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
			String json = jsonObjMap.writeValueAsString(obj);
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}


