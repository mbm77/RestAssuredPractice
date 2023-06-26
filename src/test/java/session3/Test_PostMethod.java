package session3;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Test_PostMethod {
	
	@Test
	void test03() {
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "mbm");
		jsonData.put("job", "software");
		
		RestAssured.baseURI = "https://reqres.in/api/users";
		
		RestAssured.given()
			.header("Content-Type","application/json")
			.header("accept","application/json")
			.contentType(ContentType.JSON)
			.body(jsonData.toJSONString())
		.when()
			.post()
		.then()
			.log().all().statusCode(201);
			
		
		
		
	}
}
