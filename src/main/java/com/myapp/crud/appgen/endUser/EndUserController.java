package com.myapp.crud.appgen.endUser;

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
public class EndUserController {

	@Autowired
	private EndUserService endUserService;

	@Autowired
	private CodeGroupsService codeGroupsService;
	
	private EndUserListService getEndUserListService () {
		return new EndUserListService(codeGroupsService);
	}

	public EndUserController() {
	}

	@GetMapping("/endUsers")
	////@CrossOrigin(origins = Constants.CLIENT_URL)
	public EndUserResponse list(@PageableDefault(page = Constants.DEFAULT_PAGE_NUMBER, size = Constants.DEFAULT_PAGE_SIZE)
//		  @SortDefault.SortDefaults({
//		  @SortDefault(sort = "dateRecorded", direction = Sort.Direction.DESC),
//		  @SortDefault(sort = "encounterId", direction = Sort.Direction.ASC)
//		})
		Pageable pageable) {
		System.out.println("\n*** ENTERED ==>/endUserList");
		ResponseStatus status = new ResponseStatus();
		EndUserResponse res = new EndUserResponse(status);

		Page endUserList = null;
		try {
			endUserList = endUserService.findByAll(pageable);
			status.setMessage("SUCCESS!");

//			jInfo(endUserList);
		} catch (Exception e) {
			status.setException(e);
			e.printStackTrace();
		}
		res.setEndUserList(endUserList);
		System.out.println("END ==>/endUserList");
		return res;
	}

	@DeleteMapping("/endUsers/{id}")
	////@CrossOrigin(origins = Constants.CLIENT_URL)
	public EndUserResponse delete(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/endUser/" + id);

		ResponseStatus status = new ResponseStatus();
		EndUserResponse res = new EndUserResponse(status);
		try {
			endUserService.deleteById(id);
			status.setMessage("SUCCESS!");
//			jInfo(endUser);
		} catch (Exception e) {
			e.printStackTrace();

			status.setException(e);
		}
		System.out.println("END ==>/endUser/delete/" + id);
		return res;
	}

	@GetMapping("/endUsers/{id}")
	////@CrossOrigin(origins = Constants.CLIENT_URL)
	public EndUserResponse get(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/endUser/" + id);
		EndUser endUser = null;
		ResponseStatus status = new ResponseStatus();
		EndUserResponse res = new EndUserResponse(status, getEndUserListService());
		try {
			endUser = endUserService.findById(id);
			status.setMessage("SUCCESS!");
//			jInfo(endUser);
			endUser.setPassword("");
		} catch (Exception e) {
			endUser = new EndUser();
			e.printStackTrace();

			status.setException(e);
		}

		res.setEndUser(endUser);
		System.out.println("END ==>/endUser/" + id);
		return res;
	}

	@PostMapping(path = "/endUsers", consumes = { MediaType.APPLICATION_JSON_VALUE })
	////@CrossOrigin(origins = Constants.CLIENT_URL)
	public EndUser save(@RequestBody EndUser endUser) {
		System.out.println("\n*** ENTERED ==>endUser/save");
		ResponseStatus status = new ResponseStatus();
		EndUserResponse res = new EndUserResponse(status, getEndUserListService());
		try {
//			System.out.println("#######################");
//			jInfo(endUser);
//			System.out.println("#######################");
			endUserService.save(endUser);
			status.setMessage("SUCCESS!");
//			System.out.println("=======================");
//			jInfo(endUser);
//			System.out.println("=======================");
		} catch (Exception e) {
			endUser = new EndUser();
			status.setException(e);
			e.printStackTrace();
		}
		endUser.setPassword("");
		res.setEndUser(endUser);
		System.out.println("END ==>endUser/save");
		return endUser;
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


