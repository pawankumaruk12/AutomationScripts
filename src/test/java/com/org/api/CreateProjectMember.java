package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
//Need to form request json and test it, could not find this api in crewstart
@Ignore
public class CreateProjectMember extends CommonLogin{
	@Test(enabled = false)
	public void CreatesProjectMembers() throws Exception{
		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");
		
		response = given().
				body(Files.readAllBytes(Paths.get("src/test/resources/CreateProjectMember.json"))).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "https://uat.sargent-disc.com/sdw/projectmember/create");
				
		//System.out.println(response.getBody().asString());
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		

		
	}
	
}
