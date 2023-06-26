package session6;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequstWithQueryParams {
	
	@Test
	public void usingQueryParams() {
		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri("https://reqres.in");
		requestSpec.basePath("/api/users");
		requestSpec.queryParam("page", 2).queryParam("id", 10);
		
		
		Response response = requestSpec.get();
		String responseBodyString = response.getBody().asString();
		
		System.out.println(responseBodyString);
		
		JsonPath jsonPathView = response.jsonPath();
		String first_name = jsonPathView.get("data.first_name");
		
		Assert.assertEquals(first_name, "Byron", "verify first name as mbm");
		
		
	}
}
