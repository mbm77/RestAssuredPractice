package session9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JsonObjectUsingJavaMapDemo {
	
	@Test
	public void createAuthToken() {
		// https://restful-booker.herokuapp.com/auth
		Map<String, String> mapObj = new HashMap<String,String>();
		mapObj.put("username", "admin");
		mapObj.put("password", "password123");
		
		RequestSpecification request = RestAssured.given();
		request.baseUri("https://restful-booker.herokuapp.com");
		request.basePath("/auth");
		request.header("Content-Type","application/json");
		request.body(mapObj);
		Response response = request.when().post();
		
		response.prettyPrint();
		
		Assert.assertEquals(response.getStatusCode(), 200,"check for status code");
		
	}
	
	@Test
	public void createUser() {
	/*	{
			"firstName": "Bala",
			"lastname": "Murali",
			"age": 30,
			"salary": 1000.56,
			"IsMarried": true,
			"Hobbies": [
				"Music",
				"Computer",
				"Games"
			],
			"TechSkill": {
				"Programming language":"Java",
				"WebAutomation": "Selenium",
				"API testing": "Rest Assured"
			}
		} */
		//https://reqres.in/api/users
		Map<String,Object> mapObj = new HashMap<String,Object>();
		mapObj.put("firstName","Bala");
		mapObj.put("lastname", "Murali");
		mapObj.put("age", 30);
		mapObj.put("salary", 1000.56);
		mapObj.put("IsMarried", true);
		
		ArrayList<String> hobbies = new ArrayList<>(Arrays.asList("Music","Computer","Games"));
		mapObj.put("Hobbies", hobbies);
		
		HashMap<String,String> techSkills = new HashMap<String,String>();
		techSkills.put("Programming language", "Java");
		techSkills.put("WebAutomation","Selenium");
		techSkills.put("API testing", "Rest Assured");
		mapObj.put("TechSkill", techSkills);
		
		RequestSpecification request = RestAssured.given();
		request.baseUri("https://reqres.in");
		request.basePath("/api/users");
		request.header("Content-Type", "application/json");
		request.body(mapObj);
		Response response = request.when().post();
		
		response.prettyPrint();
		
		Assert.assertEquals(response.getStatusCode(), 201);
	}

}
