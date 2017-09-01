package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
//Issue needs to be fixed
@Ignore
public class UpdateCompany extends CommonLogin {
	@Test(enabled = false)
	public void UpdateCompanys() throws Exception{
		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
		//String updatecompanyjson = "src/resources/UpdateCompany.json";
		
		resp = given().
				body(Files.readAllBytes(Paths.get("src/test/resources/UpdateCompany.json"))).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "company/update");
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
