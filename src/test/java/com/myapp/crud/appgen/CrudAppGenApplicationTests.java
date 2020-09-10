package com.myapp.crud.appgen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature; 
//import com.spring.angular.proj5.user.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudAppGenApplicationTests.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrudAppGenApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetAllUsers() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "home/list", HttpMethod.GET, entity,
				String.class);

		assertNotNull(response.getBody());
	}
//
//	@Test
//	public void testGetUserById() {
//		User user = restTemplate.getForObject(getRootUrl() + "/users/1", User.class);
//		System.out.println(user.getFirstName());
//		assertNotNull(user);
//	}
//
//	@Test
//	public void testCreateUser() {
//		User user = new User();
//		user.setEmailId("admin@gmail.com");
//		user.setFirstName("admin");
//		user.setLastName("admin");
//		user.setCreatedBy("admin");
//		user.setUpdatedby("admin");
//
//		ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/users", user, User.class);
//		assertNotNull(postResponse);
//		assertNotNull(postResponse.getBody());
//	}
//
//	@Test
//	public void testUpdatePost() {
//		int id = 1;
//		User user = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
//		user.setFirstName("admin1");
//		user.setLastName("admin2");
//
//		restTemplate.put(getRootUrl() + "/users/" + id, user);
//
//		User updatedUser = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
//		assertNotNull(updatedUser);
//	}
//
//	@Test
//	public void testDeletePost() {
//		int id = 2;
//		User user = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
//		assertNotNull(user);
//
//		restTemplate.delete(getRootUrl() + "/users/" + id);
//
//		try {
//			user = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
//		} catch (final HttpClientErrorException e) {
//			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
//		}
//	}

	private void toJson(Object obj) {
		try {
			ObjectMapper jsonObjMap = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
			String json = jsonObjMap.writeValueAsString(obj);
			System.out.println("jInfo:\n" + json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
