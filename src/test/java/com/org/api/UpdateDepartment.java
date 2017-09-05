package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
@Ignore
//Issue needs to be fixed
public class UpdateDepartment extends CommonLogin{
	@Test(enabled = false)
	public void UpdateCompanys() throws Exception{
		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");
		//String updatecompanyjson = "src/resources/UpdateCompany.json";
		
		response = given().
				body(Files.readAllBytes(Paths.get("src/test/resources/UpdateDepartment.json"))).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "company/update");
		System.out.println(response.getBody().asString());
		AssertJUnit.assertEquals( response.getStatusCode(), 200);
		if (response.getStatusCode()==200){
			System.out.println("API is working fine");
			System.out.println(response.getStatusCode());
		}
		else {
			System.out.println("API is not working fine");
			
		}
				
	}

}
