package com.org.api;

import io.restassured.http.ContentType;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
//it is not implemented, because after creating project team member, we can assign agent
@Ignore
public class AssignPersonToAgency extends CommonLogin{
	@Test(enabled = false)
	public void CreatesPersons() throws Exception {
		String jsessionId = response.cookie(JSESSIONID);
		String xsrfToken = response.cookie(XSRF_TOKEN);
		String CreateNewPerson = "src/test/resources/CreatePerson.json";
				response = given().
				body(Files.readAllBytes(Paths.get(CreateNewPerson))).
				when()
				.cookie(JSESSIONID,jsessionId)
				.cookie(XSRF_TOKEN,xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "person/create");
		System.out.println(response.getBody().asString());
		AssertJUnit.assertEquals(response.getStatusCode(), 201);
		if (response.getStatusCode() == 201) {
			System.out.println("API is working fine");
			System.out.println(response.getStatusCode());
		}
		else {
			System.out.println("API is not working");
		}	
	}

}
