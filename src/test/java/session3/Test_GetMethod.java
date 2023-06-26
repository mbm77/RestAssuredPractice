package session3;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class Test_GetMethod {
	@Test
	void test01() {
		Response response = get("https://reqres.in/api/users?page=2");
		System.out.println("Response Code :"+response.getStatusCode());
		System.out.println("Response Body :"+response.getBody().asString());
		System.out.println("Response Time : "+response.getTime());
		System.out.println("Response Header : "+response.header("Content-Type"));
		
		int expectedStatusCode = 200;
		int actualStatusCode = response.getStatusCode();
		Assert.assertEquals(actualStatusCode, expectedStatusCode);
	}
	
	@Test
	void test02() {
		baseURI = "https://reqres.in/api/users";
		given()
		.queryParam("page", "2")
		.when()
		.get()
		.then()
		.statusCode(200);
	}
}
