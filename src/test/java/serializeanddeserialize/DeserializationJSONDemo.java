package serializeanddeserialize;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class DeserializationJSONDemo {
	
	@Test
	public void createUser() {
		
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "mbm");
		jsonData.put("job", "QA");
		
		RequestSpecification request = RestAssured.given();
		
		request.baseUri("https://reqres.in");
		request.basePath("/api/users");
		
		//request.header("Content-Type","application/json");
		request.contentType(ContentType.JSON);
		Response response = request.body(jsonData.toJSONString()).post();
		ResponseBody resBody = response.getBody();
		
		
		JSONPostRequestResponse jsonRes = resBody.as(JSONPostRequestResponse.class);
		
		Assert.assertEquals(jsonRes.name, "mbm", "name should be mbm");
		Assert.assertEquals(jsonRes.job, "QA", "job should be QA");
	}
}
