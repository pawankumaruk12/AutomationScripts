package com.org.api;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import java.nio.file.Files;
import java.nio.file.Paths;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
//it is not implemented, because after creating project team member, we can assign agent
@Ignore
public class AssignPersonToAgency extends CommonLogin{
	@Test
	public void CreatesPersons() throws Exception {
		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
		String CreateNewPerson = "src/test/resources/CreatePerson.json";
				resp = given().
				body(Files.readAllBytes(Paths.get(CreateNewPerson))).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "person/create");
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
