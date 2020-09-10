package com.myapp.crud.appgen.partItem;

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
public class PartItemController {

	@Autowired
	private PartItemService partItemService;

	@Autowired
	private CodeGroupsService codeGroupsService;

	public PartItemController() {
	}

	@GetMapping("/partItems")
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public PartItemResponse list(@PageableDefault(page = Constants.DEFAULT_PAGE_NUMBER, size = Constants.DEFAULT_PAGE_SIZE)
//		  @SortDefault.SortDefaults({
//		  @SortDefault(sort = "dateRecorded", direction = Sort.Direction.DESC),
//		  @SortDefault(sort = "encounterId", direction = Sort.Direction.ASC)
//		})
		Pageable pageable) {
		System.out.println("\n*** ENTERED ==>/partItemList");
		ResponseStatus status = new ResponseStatus();
		PartItemResponse res = new PartItemResponse(status);

		Page partItemList = null;
		try {
			partItemList = partItemService.findByAll(pageable);
			status.setMessage("SUCCESS!");

//			jInfo(partItemList);
		} catch (Exception e) {
			status.setException(e);
			e.printStackTrace();
		}
		res.setPartItemList(partItemList);
		System.out.println("END ==>/partItemList");
		return res;
	}

	@DeleteMapping("/partItems/{id}")
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public PartItemResponse delete(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/partItem/" + id);

		ResponseStatus status = new ResponseStatus();
		PartItemResponse res = new PartItemResponse(status);
		try {
			partItemService.deleteById(id);
			status.setMessage("SUCCESS!");
//			jInfo(partItem);
		} catch (Exception e) {
			e.printStackTrace();

			status.setException(e);
		}
		System.out.println("END ==>/partItem/" + id);
		return res;
	}

	@GetMapping("/partItems/{id}")
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public PartItemResponse get(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/partItem/" + id);
		PartItem partItem = null;
		ResponseStatus status = new ResponseStatus();
		PartItemResponse res = new PartItemResponse(status);
		try {
			partItem = partItemService.findById(id);
			status.setMessage("SUCCESS!");
//			jInfo(partItem);
		} catch (Exception e) {
			partItem = new PartItem();
			e.printStackTrace();

			status.setException(e);
		}

		res.setPartItem(partItem);
		this.setCodeGroupsList(res);
		System.out.println("END ==>/partItem/" + id);
		return res;
	}
	@GetMapping("/partItems/by/{name}")
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public PartItemResponse getByName(@PathVariable("name") String name) {
		System.out.println("\n*** ENTERED ==>/partItem/" + name);
		List<PartItem> partItems = null;
		ResponseStatus status = new ResponseStatus();
		PartItemResponse res = new PartItemResponse(status);
		try {
			partItems = partItemService.findByName(name);
			status.setMessage("SUCCESS!");
//			jInfo(partItem);
		} catch (Exception e) {
			partItems = new ArrayList<PartItem>();
			e.printStackTrace();

			status.setException(e);
		}

		res.setPartItemsSearch(partItems);
		
		this.setCodeGroupsList(res);
		System.out.println("END ==>/partItems/name/" + name);
		return res;
	}

	private void setCodeGroupsList(PartItemResponse res) {
//		res.setCodeList(codeGroupsService.findByDistinctCode());

//		res.setCaseType1List(codeGroupsService.findByCode("CASE_TYPE_1"));
//		res.setCaseType2List(codeGroupsService.findByCode("CASE_TYPE_2"));
//		res.setCaseType3List(codeGroupsService.findByCode("CASE_TYPE_3"));
//		res.setStatusList(codeGroupsService.findByCode("STATUS"));
//		res.setStatusCodeList(codeGroupsService.findByCode("STATUS_CODE"));
	}

	@PostMapping(path = "/partItems", consumes = { MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public PartItem save(@RequestBody PartItem partItem) {
		System.out.println("\n*** ENTERED ==>partItem/save");
		ResponseStatus status = new ResponseStatus();
		PartItemResponse res = new PartItemResponse(status);
		try {
//			System.out.println("#######################");
//			jInfo(partItem);
//			System.out.println("#######################");
			partItemService.save(partItem);
			status.setMessage("SUCCESS!");
//			System.out.println("=======================");
//			jInfo(partItem);
//			System.out.println("=======================");
		} catch (Exception e) {
			partItem = new PartItem();
			status.setException(e);
			e.printStackTrace();
		}
		res.setPartItem(partItem);
		System.out.println("END ==>partItem/save");
		return partItem;
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


