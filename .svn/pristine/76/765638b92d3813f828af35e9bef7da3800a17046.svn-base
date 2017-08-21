package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
// Tested and working on 17th March 2017
public class CreateNewProjectDocument extends CommonLogin{
	@Test
	public void CreatesNewProjectDocuments() throws Exception {
		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
		String CreateNewProjectDoc = "src/test/resources/CreateNewProjectDoc.json";
				resp = given().
				body(Files.readAllBytes(Paths.get(CreateNewProjectDoc))).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "projectdocument/create");
		System.out.println(resp.getBody().asString());
		AssertJUnit.assertEquals(resp.getStatusCode(), 201);
		if (resp.getStatusCode() == 201) {
			System.out.println("API is working fine");
			System.out.println(resp.getStatusCode());
		}
		else {
			System.out.println("API is not working");
		}	
	}

}
