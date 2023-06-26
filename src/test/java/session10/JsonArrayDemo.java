package session10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JsonArrayDemo {
	
	@Test
	public void createUserUsingJsonArray() {
		
		JSONObject user1 = new JSONObject();
		user1.put("firstName", "Bala");
		user1.put("lastName", "Murali");
		user1.put("age", 30);
		user1.put("salary", 1000.56);
		
		JSONObject user2 = new JSONObject();
		user2.put("firstName", "John");
		user2.put("lastName", "Joe");
		user2.put("age", 32);
		user2.put("salary", 2000.56);
		
		JSONObject user3 = new JSONObject();
		user3.put("firstName", "Jack");
		user3.put("lastName", "Josh");
		user3.put("age", 29);
		user3.put("salary", 3000.56);
		
		JSONArray usersPayload = new JSONArray();
		
		usersPayload.add(user1);
		usersPayload.add(user2);
		usersPayload.add(user3);
		
		RequestSpecification request = RestAssured.given();
		request.baseUri("https://reqres.in/api/users");
		//request.basePath("");
		request.contentType(ContentType.JSON);
		request.body(usersPayload);
		Response response = request.when().post();
		
		response.prettyPrint();
		
		Assert.assertEquals(response.getStatusCode(), 201);
		
		
	}
	
	@Test
	public void createJsonArrayUsingList() {
		
		//JSONObject user1 = new JSONObject();\
		Map<String,Object> user1 = new HashMap<String,Object>();
		user1.put("firstName", "Bala");
		user1.put("lastName", "Murali");
		user1.put("age", 30);
		user1.put("salary", 1000.56);
		
		//JSONObject user2 = new JSONObject();
		Map<String,Object> user2 = new HashMap<String,Object>();
		user2.put("firstName", "John");
		user2.put("lastName", "Joe");
		user2.put("age", 32);
		user2.put("salary", 2000.56);
		
		//JSONObject user3 = new JSONObject();
		Map<String,Object> user3 = new HashMap<String,Object>();
		user3.put("firstName", "Jack");
		user3.put("lastName", "Josh");
		user3.put("age", 29);
		user3.put("salary", 3000.56);
		
		
		
		//JSONArray usersPayload = new JSONArray();
		//create JSON Array using List
		List<Map<String,Object>> jsonArrayListPayload = new ArrayList<>();
		jsonArrayListPayload.add(user1);
		jsonArrayListPayload.add(user2);
		jsonArrayListPayload.add(user3);
		
		RequestSpecification request = RestAssured.given();
		request.baseUri("https://reqres.in/api/users");
		//request.basePath("");
		request.contentType(ContentType.JSON);
		request.body(jsonArrayListPayload);
		Response response = request.when().post();
		
		response.prettyPrint();
		
		Assert.assertEquals(response.getStatusCode(), 201);
		
		
	}

}
