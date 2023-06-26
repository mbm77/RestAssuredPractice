package httpRequests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FirstGetRequest {
	
	@Test
	void testcase01() {
		Response response = RestAssured.get("https://reqres.in/api/users?page=2");
		System.out.println(response.asString());
		System.out.println("Status Code "+response.getStatusCode());
		
	}
}
