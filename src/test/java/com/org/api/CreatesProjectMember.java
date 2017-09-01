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
public class CreatesProjectMember extends CommonLogin{
	@Test(enabled = false)
	public void CreatesProjectMembers() throws Exception{
		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
		
		resp = given().
				body(Files.readAllBytes(Paths.get("src/test/resources/CreateProjectMember.json"))).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "https://uat.sargent-disc.com/sdw/projectmember/create");
				
		System.out.println(resp.getBody().asString());
		AssertJUnit.assertEquals(resp.getStatusCode(), 200);
		
		if (resp.getStatusCode()==200) {
			System.out.println("API is working fine");
			System.out.println(resp.getStatusCode());
		}
		else
		{System.out.println("API is not working fine");
		
		}
		
	}
	
}
