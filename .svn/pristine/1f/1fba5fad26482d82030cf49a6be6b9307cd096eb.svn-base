package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
//Tested and working on 14th March 2017

public class CreateAgency extends CommonLogin{
	@Test
	public void CreatesAgency() throws Exception {
		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
		String CreateAgency = "src/test/resources/CreateAgency.json";
				resp = given().
				body(Files.readAllBytes(Paths.get(CreateAgency))).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "agency/create");
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
