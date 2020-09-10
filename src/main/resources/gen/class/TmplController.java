package com.myapp.crud.appgen.XYclassYX;

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
public class XYCLASSYXController {

	@Autowired
	private XYCLASSYXService XYclassYXService;

	@Autowired
	private CodeGroupsService codeGroupsService;
	
	private MyCaseListService getMyCaseListService () {
		return new MyCaseListService(codeGroupsService);
	}

	public XYCLASSYXController() {
	}

	@GetMapping("/XYclassYXs")
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public XYCLASSYXResponse list(@PageableDefault(page = Constants.DEFAULT_PAGE_NUMBER, size = Constants.DEFAULT_PAGE_SIZE)
//		  @SortDefault.SortDefaults({
//		  @SortDefault(sort = "dateRecorded", direction = Sort.Direction.DESC),
//		  @SortDefault(sort = "encounterId", direction = Sort.Direction.ASC)
//		})
		Pageable pageable) {
		System.out.println("\n*** ENTERED ==>/XYclassYXList");
		ResponseStatus status = new ResponseStatus();
		XYCLASSYXResponse res = new XYCLASSYXResponse(status);

		Page XYclassYXList = null;
		try {
			XYclassYXList = XYclassYXService.findByAll(pageable);
			status.setMessage("SUCCESS!");

//			jInfo(XYclassYXList);
		} catch (Exception e) {
			status.setException(e);
			e.printStackTrace();
		}
		res.setXYCLASSYXList(XYclassYXList);
		System.out.println("END ==>/XYclassYXList");
		return res;
	}

	@DeleteMapping("/XYclassYXs/{id}")
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public XYCLASSYXResponse delete(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/XYclassYX/" + id);

		ResponseStatus status = new ResponseStatus();
		XYCLASSYXResponse res = new XYCLASSYXResponse(status);
		try {
			XYclassYXService.deleteById(id);
			status.setMessage("SUCCESS!");
//			jInfo(XYclassYX);
		} catch (Exception e) {
			e.printStackTrace();

			status.setException(e);
		}
		System.out.println("END ==>/XYclassYX/delete/" + id);
		return res;
	}

	@GetMapping("/XYclassYXs/{id}")
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public XYCLASSYXResponse get(@PathVariable("id") Long id) {
		System.out.println("\n*** ENTERED ==>/XYclassYX/" + id);
		XYCLASSYX XYclassYX = null;
		ResponseStatus status = new ResponseStatus();
		XYCLASSYXResponse res = new XYCLASSYXResponse(status, getXYCLASSYXListService());
		try {
			XYclassYX = XYclassYXService.findById(id);
			status.setMessage("SUCCESS!");
//			jInfo(XYclassYX);
		} catch (Exception e) {
			XYclassYX = new XYCLASSYX();
			e.printStackTrace();

			status.setException(e);
		}

		res.setXYCLASSYX(XYclassYX);
		System.out.println("END ==>/XYclassYX/" + id);
		return res;
	}

	@PostMapping(path = "/XYclassYXs", consumes = { MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin(origins = Constants.CLIENT_URL)
	public XYCLASSYX save(@RequestBody XYCLASSYX XYclassYX) {
		System.out.println("\n*** ENTERED ==>XYclassYX/save");
		ResponseStatus status = new ResponseStatus();
		XYCLASSYXResponse res = new XYCLASSYXResponse(status, getXYCLASSYXListService());
		try {
//			System.out.println("#######################");
//			jInfo(XYclassYX);
//			System.out.println("#######################");
			XYclassYXService.save(XYclassYX);
			status.setMessage("SUCCESS!");
//			System.out.println("=======================");
//			jInfo(XYclassYX);
//			System.out.println("=======================");
		} catch (Exception e) {
			XYclassYX = new XYCLASSYX();
			status.setException(e);
			e.printStackTrace();
		}
		res.setXYCLASSYX(XYclassYX);
		System.out.println("END ==>XYclassYX/save");
		return XYclassYX;
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
