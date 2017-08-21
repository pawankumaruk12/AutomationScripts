package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class CSCreates_Account_Post extends CommonLogin{

	@Test
	public void Create_Account() throws Exception {

	String jsessionId = resp.cookie("JSESSIONID");
	String xsrfToken = resp.cookie("XSRF-TOKEN");
	String createAcc_json_path = "src/test/resources/createAccount.json";

	resp = given().
			body(Files.readAllBytes(Paths.get(createAcc_json_path))).
			when()
			.cookie("JSESSIONID",jsessionId)
			.cookie("XSRF-TOKEN",xsrfToken).
			contentType(ContentType.JSON).
			//post(loginapiPath);
			post(API_PATH + "account/create");
	
	
			System.out.println(resp.getBody().asString());
			AssertJUnit.assertEquals( resp.getStatusCode(), 201);
			if (resp.getStatusCode()==201){
				System.out.println("API is working fine");
				System.out.println(resp.getStatusCode());
			}
			else {
				System.out.println("API is not working fine");
				
			}
		
			
	}

}
