package com.myapp.crud.appgen.home;

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
public class HomeController {

	@Autowired
	private HomeService homeService;

	@Autowired
	private CodeGroupsService codeGroupsService;

	public HomeController() {
	}

	@GetMapping("/home/list")
	//@CrossOrigin(origins = Constants.CLIENT_URL)
	public HomeResponse list(@PageableDefault(page = Constants.DEFAULT_PAGE_NUMBER, size = Constants.DEFAULT_PAGE_SIZE)
//		  @SortDefault.SortDefaults({
//		  @SortDefault(sort = "dateRecorded", direction = Sort.Direction.DESC),
//		  @SortDefault(sort = "encounterId", direction = Sort.Direction.ASC)
//		})
		Pageable pageable) {
		System.out.println("\n*** ENTERED ==>/homeList");
		ResponseStatus status = new ResponseStatus();
		HomeResponse res = new HomeResponse(status);

		Page homeList = null;
		try {
			homeList = homeService.findByAll(pageable);
			status.setMessage("SUCCESS!");

//			jInfo(homeList);
		} catch (Exception e) {
			status.setException(e);
			e.printStackTrace();
		}
		res.setHomeList(homeList);
		System.out.println("END ==>/homeList");
		return res;
	}

	@GetMapping("/home/delete/{id}")
	//@CrossOrigin(origins = Constants.CLIENT_URL)
	public HomeResponse delete(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/home/delete/" + id);

		ResponseStatus status = new ResponseStatus();
		HomeResponse res = new HomeResponse(status);
		try {
			homeService.deleteById(id);
			status.setMessage("SUCCESS!");
//			jInfo(home);
		} catch (Exception e) {
			e.printStackTrace();

			status.setException(e);
		}
		System.out.println("END ==>/home/delete/" + id);
		return res;
	}

	@GetMapping("/home/{id}")
	//@CrossOrigin(origins = Constants.CLIENT_URL)
	public HomeResponse get(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/home/" + id);
		Home home = null;
		ResponseStatus status = new ResponseStatus();
		HomeResponse res = new HomeResponse(status);
		try {
			home = homeService.findById(id);
			status.setMessage("SUCCESS!");
//			jInfo(home);
		} catch (Exception e) {
			home = new Home();
			e.printStackTrace();

			status.setException(e);
		}

		res.setHome(home);
		this.setCodeGroupsList(res);
		System.out.println("END ==>/home/" + id);
		return res;
	}

	private void setCodeGroupsList(HomeResponse res) {
//		res.setCodeList(codeGroupsService.findByDistinctCode());

//		res.setCaseType1List(codeGroupsService.findByCode("CASE_TYPE_1"));
//		res.setCaseType2List(codeGroupsService.findByCode("CASE_TYPE_2"));
//		res.setCaseType3List(codeGroupsService.findByCode("CASE_TYPE_3"));
//		res.setStatusList(codeGroupsService.findByCode("STATUS"));
//		res.setStatusCodeList(codeGroupsService.findByCode("STATUS_CODE"));
	}

	@PostMapping(path = "/home/save", consumes = { MediaType.APPLICATION_JSON_VALUE })
	//@CrossOrigin(origins = Constants.CLIENT_URL)
	public Home save(@RequestBody Home home) {
		System.out.println("\n*** ENTERED ==>home/save");
		ResponseStatus status = new ResponseStatus();
		HomeResponse res = new HomeResponse(status);
		try {
//			System.out.println("#######################");
//			jInfo(home);
//			System.out.println("#######################");
			homeService.save(home);
			status.setMessage("SUCCESS!");
//			System.out.println("=======================");
//			jInfo(home);
//			System.out.println("=======================");
		} catch (Exception e) {
			home = new Home();
			status.setException(e);
			e.printStackTrace();
		}
		res.setHome(home);
		System.out.println("END ==>home/save");
		return home;
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


