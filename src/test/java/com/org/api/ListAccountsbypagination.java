package com.org.api;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
//Working on 1st Sep 2017
public class ListAccountsbypagination extends CommonLogin {
	@Test
	public void List_Accounts() throws Exception {
	
	String jsessionId =  response.cookie("JSESSIONID");
	String xsrfToken =  response.cookie("XSRF-TOKEN");
	
	
	response = given().
			body(Files.readAllBytes(Paths.get("src/test/resources/ListByAccounts.json"))).
			when()
			.cookie("JSESSIONID",jsessionId)
			.cookie("XSRF-TOKEN",xsrfToken).
			contentType(ContentType.JSON).
			//post(loginapiPath);
			//post("http://192.168.56.139:8080/sdw/rest/account/list");
			post(CommonLogin.API_PATH + "account/list");
	
			//System.out.println(response.getBody().asString());
			AssertJUnit.assertEquals( response.getStatusCode(), 200);

			
			
	}

}
