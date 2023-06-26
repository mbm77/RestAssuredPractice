package session7;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class QueryRequestSpecification {
	
	@Test
	public void createUser() {
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", "mbm");
		jsonObj.put("job", "QA");
		
		RequestSpecification request = RestAssured.given();
		request.baseUri("https://reqres.in").basePath("/api/users").contentType(ContentType.JSON)
		.body(jsonObj.toJSONString()).header("header-name","header-value");
		QueryableRequestSpecification queryRequest = SpecificationQuerier.query(request);
		
		String baseUri = queryRequest.getBaseUri();
		System.out.println("Base URI :"+baseUri);
		
		String basePath = queryRequest.getBasePath();
		System.out.println(basePath);
		
		String body = queryRequest.getBody();
		System.out.println(body);
		
		Headers headers = queryRequest.getHeaders();
		
		for(Header header : headers) {
			System.out.println("Header Name: "+header.getName()+" Header Value: "+header.getValue());
		}
		
	}
}
