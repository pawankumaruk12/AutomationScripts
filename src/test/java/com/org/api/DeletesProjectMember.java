package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

// No need to test this api, as this api is not in use
public class DeletesProjectMember extends CommonLogin{
	@Test(enabled = false)
	public void DeleteProjectMembers() throws Exception{
		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
	//	PROJECTMEMBER_ID = 
		resp = given().
				body(Files.readAllBytes(Paths.get("src/test/resources/CompanyType.json"))).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "ProjectMember/delete/");
				
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
