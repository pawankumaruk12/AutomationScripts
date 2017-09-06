package com.org.api;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
//@Ignore
public class AccountsType extends CommonLogin {
	@Test
	public void Accounts_Types() throws Exception {

	String jsessionId =  response.cookie("JSESSIONID");
	String xsfrToken =  response.cookie("XSRF-TOKEN");
	
	 response = given().
			body(Files.readAllBytes(Paths.get("src/test/resources/AccountType.json"))).
			when()
			.cookie("JSESSIONID",jsessionId)
			.cookie("XSRF-TOKEN",xsfrToken)
			.contentType(ContentType.JSON).
			//post(loginapiPath);
			post(API_PATH + "account/types");
	
	
			//System.out.println(response.getBody().asString());
			//AssertJUnit.assertEquals( response.getStatusCode(), 200);

			
			
	}

}
