package session3;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Test_DeleteMethod {
	
	@Test
	void test06() {
		RestAssured.baseURI = "https://reqres.in/api/users/82";
		
		RestAssured.given()
			.when()
				.delete()
			.then()
				.statusCode(204)
				.log().all();
	}
}
