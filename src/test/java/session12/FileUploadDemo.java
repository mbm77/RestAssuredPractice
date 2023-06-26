package session12;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FileUploadDemo {

	@Test
	public void uploadFile() {
		
		//For Single File Upload
		/*
		 *  File testFile = new File("C:\\Users\\user\\Desktop\\testfile.txt");
		
		RequestSpecification request = RestAssured.given();
		request.baseUri("http://httpbin.org/post");
		
		request.multiPart("file",testFile);
		request.header("Content-Type","multipart/form-data");
		Response response = request.post();  */
		
		//For Multiple Files Upload
		
		File testFile = new File("C:\\Users\\user\\Desktop\\testfile.txt");
		File testFile2 = new File("C:\\Users\\user\\Desktop\\testfile2.txt");
		
		RequestSpecification request = RestAssured.given();
		request.baseUri("http://httpbin.org/post");
		
		request.multiPart("files",testFile);
		request.multiPart("files",testFile2);
		request.header("Content-Type","multipart/form-data");
		Response response = request.post();
		
		response.prettyPrint();
		
		Assert.assertEquals(response.getStatusCode(),200, "check for status code");
		
	}
	
	@Test
	public void imageUpload() {
		File imageFile = new File("C:\\Users\\user\\Downloads\\mbm.jpg");
		RequestSpecification request = RestAssured.given();
		request.baseUri("https://petstore.swagger.io/v2/pet/1/uploadImage");
		request.header("Content-Type","multipart/form-data");
		request.multiPart("file",imageFile);
		Response response = request.post();
		response.then().log().all();
		
		
	}
}
