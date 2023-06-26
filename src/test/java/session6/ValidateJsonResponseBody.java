package session6;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class ValidateJsonResponseBody {
	
	@Test
	public void userListResponseBody() {
		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri("https://reqres.in");
		requestSpec.basePath("/api/users?page=2");
		Response response = requestSpec.get();
		ResponseBody responseBody = response.getBody();
	
		/*	String jsonString = responseBody.asString();
		System.out.println(jsonString);
		Assert.assertEquals(jsonString.contains("Eve"), true, "Check for presence of Eve"); */
		
		JsonPath jsonPathView = responseBody.jsonPath();
		String first_name = jsonPathView.get("data[3].first_name").toString();
		String avatar = jsonPathView.get("data[2].avatar");
		
		System.out.println("Avatar :"+avatar);
		
		Assert.assertEquals(first_name, "Eve", "check for presence of Eve");
	}
}
