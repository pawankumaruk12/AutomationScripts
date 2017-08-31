package com.org.api;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import java.nio.file.Files;
import java.nio.file.Paths;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
//@Ignore
public class Accounts_Type extends CommonLogin {
	@Test
	public void Accounts_Types() throws Exception {

	String jsessionId =  resp.cookie("JSESSIONID");
	String xsfrToken =  resp.cookie("XSRF-TOKEN");
	
	 resp = given().
			body(Files.readAllBytes(Paths.get("src/test/resources/AccountType.json"))).
			when()
			.cookie("JSESSIONID",jsessionId)
			.cookie("XSRF-TOKEN",xsfrToken)
			.contentType(ContentType.JSON).
			//post(loginapiPath);
			post(API_PATH + "account/types");
	
	
			System.out.println(resp.getBody().asString());
			AssertJUnit.assertEquals( resp.getStatusCode(), 200);
			if (resp.getStatusCode()==200){
				System.out.println("API is working fine");
				System.out.println(resp.getStatusCode());
			}
			else {
				System.out.println("API is not working fine");
				
			}
			
			
	}

}
