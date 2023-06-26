package session3;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Test_PatchMethod {
	
	@Test
	void test05() {
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "Bala Murali");
		jsonData.put("job", "Test Analyst");
		
		RestAssured.baseURI = "https://reqres.in/api/users/82";
		RestAssured.given()
			.header("Content-Type","application/json")
			.contentType(ContentType.JSON)
			.body(jsonData.toJSONString())
		.when()
			.patch()
		.then()
			.statusCode(200).log().all();
			
	}
}
