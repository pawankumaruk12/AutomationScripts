package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
//@Ignore
//Issue needs to be fixed
public class UpdateDepartment extends CommonLogin{
	@Test//(enabled = false)
	public void UpdateDepartment() throws Exception{
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

		AssertJUnit.assertEquals( response.getStatusCode(), 200);

				
	}

}
