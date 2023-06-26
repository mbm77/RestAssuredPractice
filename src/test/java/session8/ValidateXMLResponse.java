package session8;

import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class ValidateXMLResponse {

	@Test
	public void GetTravellerdata() {
		//http://restapi.adequateshop.com/api/Traveler?page=1
		
		RequestSpecification request = RestAssured.given();
		request.baseUri("http://restapi.adequateshop.com");
		request.basePath("/api/Traveler");
		request.queryParam("page","1");
		request.header("accept","application/xml");
		Response response = request.get();
		response.prettyPrint();
		
		//response.then().body("Pet.name", Matchers.equalTo("doggie"));
		
		XmlPath xmlPathObj = new XmlPath(response.asString());
		String travellerName = xmlPathObj.get("TravelerinformationResponse.travelers.Travelerinformation[2].name").toString();
		
		List<String> travellers = xmlPathObj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		List<String> names = xmlPathObj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		boolean found = false;
		for(String name : names) {
			if(name.equals("vano")) {
				found = true;
				break;
			}
		}
		Assert.assertEquals(travellers.size(), 10);
		Assert.assertEquals(found, true);
		Assert.assertEquals(travellerName,"vano", "name should be Developer");
		 
	}
	
	@Test(enabled = false)
	public void AppPet() {
		
		//https://petstore.swagger.io/v2/pet
		String jsonData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
				+ "<Pet>\r\n"
				+ "	<id>0</id>\r\n"
				+ "	<Category>\r\n"
				+ "		<id>0</id>\r\n"
				+ "		<name>string</name>\r\n"
				+ "	</Category>\r\n"
				+ "	<name>doggie</name>\r\n"
				+ "	<photoUrls>\r\n"
				+ "		<photoUrl>string</photoUrl>\r\n"
				+ "	</photoUrls>\r\n"
				+ "	<tags>\r\n"
				+ "		<Tag>\r\n"
				+ "			<id>0</id>\r\n"
				+ "			<name>string</name>\r\n"
				+ "		</Tag>\r\n"
				+ "	</tags>\r\n"
				+ "	<status>available</status>\r\n"
				+ "</Pet>";
		RequestSpecification request = RestAssured.given();
		request.baseUri("https://petstore.swagger.io");
		request.basePath("/v2/pet");
		request.header("accept","application/xml");
		request.header("Content-Type","application/xml");
		request.body(jsonData);
		Response response = request.post();
		response.prettyPrint();
		
		response.then().body("Pet.name", Matchers.equalTo("doggie"));
	
		
	}
}
