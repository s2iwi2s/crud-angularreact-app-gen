package com.myapp.crud.appgen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Constants { 
	
	public static final String CLIENT_URL = "http://localhost:3000";// 3000 4200
	
	public static final int DEFAULT_PAGE_NUMBER = 0;
	public static final int DEFAULT_PAGE_SIZE = 25;
	


	public static void jInfo(Object obj) {
		try {
			ObjectMapper jsonObjMap = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
			String json = jsonObjMap.writeValueAsString(obj);
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
