package com.org.api;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import com.org.api.unittest.ProjectService;

//working on 31st Aug and last project should not have children
@Ignore
public class DeleteProject extends CommonLogin{
	public static String PROJECT_ID;

	@Test(enabled = false)
	public void DeleteProjects() throws Exception {
		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");
		
		PROJECT_ID = ProjectService.getLastProjectId(null, jsessionId, xsrfToken);
		
		
	response = given().
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
			contentType(ContentType.JSON).
				post(API_PATH + "project/delete/" + PROJECT_ID);
		System.out.println(response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(),200);
	if (response.getStatusCode() == 200) {
		System.out.println("API is working fine");
			System.out.println(response.getStatusCode());
	}
		else
			System.out.println("API is not working fine");
		
	}
}


