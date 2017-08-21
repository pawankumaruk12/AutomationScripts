package com.org.api;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import com.org.api.unittest.ProjectService;

public class DeleteProject extends CommonLogin{
	public static String PROJECT_ID;
	
	@Test
	public void DeleteProjects() throws Exception {
		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
		
		PROJECT_ID = ProjectService.getLastProjectId(null, jsessionId, xsrfToken);
		
		
	resp = given().
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
			contentType(ContentType.JSON).
				post(API_PATH + "project/delete/" + PROJECT_ID);
		System.out.println(resp.getBody().asString());
		Assert.assertEquals(resp.getStatusCode(),200);
	if (resp.getStatusCode() == 200) {
		System.out.println("API is working fine");
			System.out.println(resp.getStatusCode());
	}
		else
			System.out.println("API is not working fine");
		
	}
}


