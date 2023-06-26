package session11;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JSONObjectUsingJacksonAPI {

	@Test
	public void createUserUsingJacksonAPI() {
		/*	{
			"firstName": "Bala",
			"lastname": "Murali",
			"age": 30,
			"salary": 1000.56,
			"IsMarried": true,
			"Hobbies": [
				"Music",
				"Computer",
				"Games"
			],
			"TechSkill": {
				"Programming language":"Java",
				"WebAutomation": "Selenium",
				"API testing": "Rest Assured"
			}
		} */

		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode userData = objectMapper.createObjectNode();
		userData.put("firstName", "Bala");
		userData.put("lastName", "Murali");
		userData.put("age", 30);
		userData.put("salary", 4000.46);
		userData.put("IsMarried", false);
		userData.set("Hobbies", objectMapper.convertValue(Arrays.asList("Cooking","Music"), JsonNode.class));

		ObjectNode techSkills = objectMapper.createObjectNode();
		techSkills.put("Programming language", "Java");
		techSkills.put("WebAutomation", "Selenium");
		techSkills.put("API testing", "Rest Assured");

		userData.set("TeckSkill", techSkills);

		//print user details as JSON object

		try {
			String userDetailsAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userData);
			System.out.println(userDetailsAsString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//retrieve firstName field value
		String firstName = userData.get("firstName").asText();
		System.out.println(firstName);

		//retrieve IsMarried field value
		boolean isMarried = userData.get("IsMarried").asBoolean();
		System.out.println(isMarried);
		
		//retrieve WebAutomation field value
		String webAutomationValue = userData.get("TeckSkill").get("WebAutomation").asText();
		System.out.println(webAutomationValue);
		
		//print all field names
		System.out.println("********* Print All Field Names *********");
		Iterator<String> fields = userData.fieldNames();
		while(fields.hasNext()) {
			System.out.println(fields.next());
		}
		
		//print all field values
		System.out.println("*********** Print All field values ************");
		
		Iterator<JsonNode> fieldValues = userData.elements();
		while(fieldValues.hasNext()) {
			System.out.println(fieldValues.next());
		}
		
		//print all fields name and value
		System.out.println("**********Print All Fields Name And Value");
		Iterator<Entry<String,JsonNode>> itr = userData.fields();
		
		while(itr.hasNext()) {
			Entry<String,JsonNode> entry = itr.next();
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}
		
		//Remove field firstName from JSON object
		String firstName_removed = userData.remove("firstName").asText();
		System.out.println(firstName_removed);
		
		//update lastName
		userData.put("lastName", "Mannepalli");
		
		//update nested JSON object field
		techSkills.put("Programming language", "Core Java");
		userData.set("TeckSkill", techSkills);
		try {
			String userDetailsAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userData);
			System.out.println("JSON Object afetr remove firstName: \n"+userDetailsAsString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("******** Response Data *******");
		RequestSpecification request = RestAssured.given();
		request.baseUri("https://reqres.in/api/users");
		//request.header("Content-Type","application/json");
		request.contentType(ContentType.JSON);
		request.body(userData);
		Response response = request.when().post();
		response.prettyPrint();
	}

}
















