package com.org.api;



import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
@Ignore
// working on 31st Aug, but change the company name on json
public class Create_Company extends CommonLogin {
	@Test
	public void Create_Companys() throws Exception{
	
		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
		String createCompanyJson = "src/test/resources/CreateCompany.json";
		
		resp = given().
		body(Files.readAllBytes(Paths.get(createCompanyJson))).
		when()
		.cookie("JSESSIONID",jsessionId)
		.cookie("XSRF-TOKEN",xsrfToken).
		contentType(ContentType.JSON).
		post(API_PATH + "company/create");
		
		System.out.println(resp.getBody().asString());
		AssertJUnit.assertEquals(resp.getStatusCode(), 201);
		if (resp.getStatusCode()==201){
			System.out.println("API is working fine");
			System.out.println(resp.getStatusCode());
		}
		else {
			System.out.println("API is not working");
		}
	
		
	
	}

}
