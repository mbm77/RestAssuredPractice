package Authorization;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Authorization {
	static String access_token;
	@Test
	public void basic_auth() {
		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri("https://postman-echo.com");
		requestSpec.basePath("/basic-auth");
		requestSpec.auth().preemptive().basic("postman", "password");
		Response response = requestSpec.get();
		String jsonBody = response.getBody().asString();
		System.out.println("response :"+jsonBody);
		Assert.assertEquals(response.statusCode(), 200);
		
	}
	
	@Test
	public void digest_auth() {
		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri("https://httpbin.org");
		requestSpec.basePath("/digest-auth/undefined/mbm/mbm");
		requestSpec.auth().digest("mbm", "mbm").get();
		
		Response response = requestSpec.get();
		String jsonBody = response.getBody().asString();
		System.out.println("response :"+jsonBody);
		Assert.assertEquals(response.statusCode(), 200);
		
	}
	
	@Test
	public void bearer_token() {
		
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "murali");
		jsonData.put("email","mbmbm@gmail.com");
		jsonData.put("gender","male");
		jsonData.put("status","active");
		
		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri("https://gorest.co.in");
		requestSpec.basePath("/public/v2/users");
		
		String token = "8a83d528fb25dfa5591454ba999b19ea73493e717c0e20b4b6fe7117bb5822fa";
		requestSpec.header("Authorization","Bearer "+token).contentType(ContentType.JSON);
		requestSpec.body(jsonData.toJSONString());
		
		Response response = requestSpec.post();
		String jsonBody = response.getBody().asString();
		System.out.println("response :"+jsonBody);
		Assert.assertEquals(response.statusCode(), 201);
		
	}
	
	//oauth2
	
	@Test
	public void api_key_auth() {
		//https://api.openweathermap.org/data/2.5/weather?lat=12.9719&lon=77.5937&appid=956d57a7e8d94323364835609d1aaa68
		
		RequestSpecification requestSpec = RestAssured.given();
		
		requestSpec.baseUri("https://api.openweathermap.org");
		requestSpec.basePath("/data/2.5/weather");
		requestSpec.queryParam("lat", "12.9719").queryParam("lon", "77.5937").queryParam("appid", "956d57a7e8d94323364835609d1aaa68");
		
		Response response = requestSpec.get();
		
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test
	public void generateAccessToken() {
		//https://api-m.sandbox.paypal.com/v1/oauth2/token
		
		String Client_Id = "ASI3c-That4CioOaSpKp9EqhP0fJf0ODmQbuVyqqwLy4wfqQVT666amWGIofCPrHsXrcnQU4dUF3PH0E";
		String Client_Secret = "EFgf2EAtwHMFERxUa0H6u-z-4yno2eL7lGl1AWqHm01TQtXisS_x5ojmQll7yV464Uh3VirGj8shUJ0i";
		
		RequestSpecification requestSpec = RestAssured.given();
		
		requestSpec.baseUri("https://api-m.sandbox.paypal.com");
		requestSpec.basePath("/v1/oauth2/token");
		
		Response response = requestSpec.auth().preemptive().basic(Client_Id, Client_Secret).param("grant_type", "client_credentials").post();
		//response.prettyPrint();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		access_token = response.getBody().path("access_token");
		
		System.out.println("access_token : "+access_token);
	}
	
	@Test(dependsOnMethods="generateAccessToken")
	public void invoiceList() {
		//https://api-m.sandbox.paypal.com/v1/invoicing/invoices?page=3&page_size=4&total_count_required=true
		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri("https://api-m.sandbox.paypal.com");
		requestSpec.basePath("/v1/invoicing/invoices");
		requestSpec.queryParam("page", "3").queryParam("page_size", "4").queryParam("total_count_required", "true");
		
		requestSpec.header("Authorization","Bearer "+access_token); 
		
		Response response = requestSpec.when().get();
		
		response.prettyPrint();
		
	}
	
}
