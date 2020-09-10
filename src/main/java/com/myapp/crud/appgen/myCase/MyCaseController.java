package com.myapp.crud.appgen.myCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.myapp.crud.appgen.Constants;
import com.myapp.crud.appgen.ResponseStatus;
import com.myapp.crud.appgen.codeGroups.CodeGroupsService;

@RestController()
@RequestMapping(path = "/api")
public class MyCaseController {

	@Autowired
	private MyCaseService myCaseService;

	@Autowired
	private CodeGroupsService codeGroupsService;
	
	private MyCaseListService getMyCaseListService () {
		return new MyCaseListService(codeGroupsService);
	}

	public MyCaseController() {
	}

	@GetMapping("/myCases")
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public MyCaseResponse list(@PageableDefault(page = Constants.DEFAULT_PAGE_NUMBER, size = Constants.DEFAULT_PAGE_SIZE)
//		  @SortDefault.SortDefaults({
//		  @SortDefault(sort = "dateRecorded", direction = Sort.Direction.DESC),
//		  @SortDefault(sort = "encounterId", direction = Sort.Direction.ASC)
//		})
		Pageable pageable) {
		System.out.println("\n*** ENTERED ==>/myCaseList");
		ResponseStatus status = new ResponseStatus();
		MyCaseResponse res = new MyCaseResponse(status);

		Page myCaseList = null;
		try {
			myCaseList = myCaseService.findByAll(pageable);
			status.setMessage("SUCCESS!");

//			jInfo(myCaseList);
		} catch (Exception e) {
			status.setException(e);
			e.printStackTrace();
		}
		res.setMyCaseList(myCaseList);
		System.out.println("END ==>/myCaseList");
		return res;
	}

	@DeleteMapping("/myCases/{id}")
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public MyCaseResponse delete(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/myCase/" + id);

		ResponseStatus status = new ResponseStatus();
		MyCaseResponse res = new MyCaseResponse(status);
		try {
			myCaseService.deleteById(id);
			status.setMessage("SUCCESS!");
//			jInfo(myCase);
		} catch (Exception e) {
			e.printStackTrace();

			status.setException(e);
		}
		System.out.println("END ==>/myCase/delete/" + id);
		return res;
	}

	@GetMapping("/myCases/{id}")
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public MyCaseResponse get(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/myCase/" + id);
		MyCase myCase = null;
		ResponseStatus status = new ResponseStatus();
		MyCaseResponse res = new MyCaseResponse(status, getMyCaseListService());
		try {
			myCase = myCaseService.findById(id);
			status.setMessage("SUCCESS!");
//			jInfo(myCase);
		} catch (Exception e) {
			myCase = new MyCase();
			e.printStackTrace();

			status.setException(e);
		}

		res.setMyCase(myCase);
		System.out.println("END ==>/myCase/" + id);
		return res;
	}

	@PostMapping(path = "/myCases", consumes = { MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public MyCase save(@RequestBody MyCase myCase) {
		System.out.println("\n*** ENTERED ==>myCase/save");
		ResponseStatus status = new ResponseStatus();
		MyCaseResponse res = new MyCaseResponse(status, getMyCaseListService());
		try {
//			System.out.println("#######################");
//			jInfo(myCase);
//			System.out.println("#######################");
			myCaseService.save(myCase);
			status.setMessage("SUCCESS!");
//			System.out.println("=======================");
//			jInfo(myCase);
//			System.out.println("=======================");
		} catch (Exception e) {
			myCase = new MyCase();
			status.setException(e);
			e.printStackTrace();
		}
		res.setMyCase(myCase);
		System.out.println("END ==>myCase/save");
		return myCase;
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


