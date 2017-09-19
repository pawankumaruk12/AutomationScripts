package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
//working on 1st Sep 2017
public class ProjectList extends CommonLogin {
	@Test
	public void List_Api() throws Exception {

	String jsessionId =  response.cookie("JSESSIONID");
	String xsrfToken =  response.cookie("XSRF-TOKEN");
	
	
	response = given().
			body(Files.readAllBytes(Paths.get("src/test/resources/project_list.json"))).
			when()
			.cookie("JSESSIONID",jsessionId)
			.cookie("XSRF-TOKEN",xsrfToken).
			contentType(ContentType.JSON).
			post(API_PATH + "project/list");

			AssertJUnit.assertEquals( response.getStatusCode(), 200);

			
	}

}
