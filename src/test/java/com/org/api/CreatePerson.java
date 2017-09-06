package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
@Ignore
//tested and working on 13th March and change email id and phone number in json everytime

public class CreatePerson extends CommonLogin{
	@Test(enabled = false)
	public void CreatesPersons() throws Exception {
		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");
		String CreateNewPerson = "src/test/resources/CreatePerson.json";
				response = given().
				body(Files.readAllBytes(Paths.get(CreateNewPerson))).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "person/create");
		//System.out.println(response.getBody().asString());
		AssertJUnit.assertEquals(response.getStatusCode(), 201);

	}

}
