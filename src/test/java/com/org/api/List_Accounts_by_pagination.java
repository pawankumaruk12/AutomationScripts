package com.org.api;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
//Working on 1st Sep 2017
public class List_Accounts_by_pagination extends CommonLogin {
	@Test
	public void List_Accounts() throws Exception {
	
	String jsessionId =  resp.cookie("JSESSIONID");
	String xsrfToken =  resp.cookie("XSRF-TOKEN");
	
	
	resp = given().
			body(Files.readAllBytes(Paths.get("src/test/resources/ListByAccounts.json"))).
			when()
			.cookie("JSESSIONID",jsessionId)
			.cookie("XSRF-TOKEN",xsrfToken).
			contentType(ContentType.JSON).
			//post(loginapiPath);
			//post("http://192.168.56.139:8080/sdw/rest/account/list");
			post(CommonLogin.API_PATH + "account/list");
	
			System.out.println(resp.getBody().asString());
			AssertJUnit.assertEquals( resp.getStatusCode(), 200);
			if (resp.getStatusCode()==200){
				System.out.println("API is working fine");
				System.out.println(resp.getStatusCode());
				System.out.println(resp.asString());
			}
			else {
				System.out.println("API is not working fine");
				
			}
			
			
	}

}
