package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
// Tested and working on 17th March 2017, change the details on Json
@Ignore
public class CreateNewProjectDocument extends CommonLogin{
	@Test(enabled = false)
	public void CreatesNewProjectDocuments() throws Exception {
		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");
		String CreateNewProjectDoc = "src/test/resources/CreateNewProjectDoc.json";
				response = given().
				body(Files.readAllBytes(Paths.get(CreateNewProjectDoc))).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "projectdocument/create");
		//System.out.println(response.getBody().asString());
		AssertJUnit.assertEquals(response.getStatusCode(), 201);

	}

}
