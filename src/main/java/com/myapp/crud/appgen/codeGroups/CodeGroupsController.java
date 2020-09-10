package com.myapp.crud.appgen.codeGroups;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class CodeGroupsController {

	@Autowired
	private CodeGroupsService codeGroupsService;

//	@Autowired
//	private CodeGroupsService codeGroupsService;

	public CodeGroupsController() {
	}

	@GetMapping("/codeGroups")
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public CodeGroupsResponse list(Pageable pageable) {
		System.out.println("\n*** ENTERED ==>/codeGroupsList");
		ResponseStatus status = new ResponseStatus();
		CodeGroupsResponse res = new CodeGroupsResponse(status);

		Page codeGroupsList = null;
		try {
			codeGroupsList = codeGroupsService.findByAll(pageable);
			status.setMessage("SUCCESS!");

//			jInfo(codeGroupsList);
		} catch (Exception e) { 
			status.setException(e);
			e.printStackTrace();
		}
		res.setCodeGroupsList(codeGroupsList);
		System.out.println("END ==>/codeGroupsList");
		return res;
	}

	@DeleteMapping("/codeGroups/{id}")
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public CodeGroupsResponse delete(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/codeGroups/delete/" + id);

		ResponseStatus status = new ResponseStatus();
		CodeGroupsResponse res = new CodeGroupsResponse(status);
		try {
			codeGroupsService.deleteById(id);
			status.setMessage("SUCCESS!");
//			jInfo(codeGroups);
		} catch (Exception e) {
			e.printStackTrace();

			status.setException(e);
		}
		System.out.println("END ==>/codeGroups/delete/" + id);
		return res;
	}

	@GetMapping("/codeGroups/{id}")
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public CodeGroupsResponse get(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/codeGroups/" + id);
		CodeGroups codeGroups = null;
		ResponseStatus status = new ResponseStatus();
		CodeGroupsResponse res = new CodeGroupsResponse(status);
		try {
			codeGroups = codeGroupsService.findById(id);
			status.setMessage("SUCCESS!");
//			jInfo(codeGroups);
		} catch (Exception e) {
			codeGroups = new CodeGroups();
			e.printStackTrace();

			status.setException(e);
		}

		res.setCodeGroups(codeGroups);
		this.setCodeGroupsList(res);
		System.out.println("END ==>/codeGroups/" + id);
		return res;
	}

	private void setCodeGroupsList(CodeGroupsResponse res) {
		res.setCodeList(codeGroupsService.findByDistinctCode());
//		res.setCodeList(codeGroupsService.findByCode("Code"));

//		res.setCaseType1List(codeGroupsService.findByCode("CASE_TYPE_1"));
//		res.setCaseType2List(codeGroupsService.findByCode("CASE_TYPE_2"));
//		res.setCaseType3List(codeGroupsService.findByCode("CASE_TYPE_3"));
//		res.setStatusList(codeGroupsService.findByCode("STATUS"));
//		res.setStatusCodeList(codeGroupsService.findByCode("STATUS_CODE"));
	}

	@PostMapping(path = "/codeGroups", consumes = { MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public CodeGroups save(@RequestBody CodeGroups codeGroups) {
		System.out.println("\n*** ENTERED ==>codeGroups/save");
		ResponseStatus status = new ResponseStatus();
		CodeGroupsResponse res = new CodeGroupsResponse(status);
		try {
//			System.out.println("#######################");
//			jInfo(codeGroups);
//			System.out.println("#######################");
			codeGroupsService.save(codeGroups);
			status.setMessage("SUCCESS!");
//			System.out.println("=======================");
//			jInfo(codeGroups);
//			System.out.println("=======================");
		} catch (Exception e) {
			codeGroups = new CodeGroups();
			status.setException(e);
			e.printStackTrace();
		}
		res.setCodeGroups(codeGroups);
		System.out.println("END ==>codeGroups/save");
		return codeGroups;
	}
}


