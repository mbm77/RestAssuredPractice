package session13;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LocalJSONServerAPITesting {
	
	@Test(priority=1)
	public void postUserData() {
		RequestSpecification request = RestAssured.given();
		request.baseUri("http://localhost:3000/users");
		Response response = request.get();
		response.prettyPrint();
		
		Assert.assertEquals(response.getStatusCode(), 200,"check for status code 200");
		
	}
	
	@Test(priority=2)
	public void createUser() {
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", "Bala");
		jsonObj.put("age", 29);
		
		RequestSpecification request = RestAssured.given();
		request.baseUri("http://localhost:3000/users");
		request.header("Content-Type","application/json");
		request.body(jsonObj);
		
		Response response = request.post();
		
		response.prettyPrint();
		
		Assert.assertEquals(response.getStatusCode(),201,"check for status code 201");
		}
	
	@Test(priority=3)
	public void updateUser() {
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", "Bala Murali");
		jsonObj.put("age", 28);
		
		RequestSpecification request = RestAssured.given();
		request.baseUri("http://localhost:3000");
		request.basePath("/users/1");
		request.header("Content-Type","application/json");
		request.body(jsonObj);
		Response response = request.put();
		response.prettyPrint();
		
		Assert.assertEquals(response.getStatusCode(), 200,"check for status code 200");
	}
	
	@Test(priority=4)
	public void deleteUser() {
		RequestSpecification request = RestAssured.given();
		request.baseUri("http://localhost:3000/userS/3");
		Response response = request.delete();
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200,"check for status code 204");
	}
}
