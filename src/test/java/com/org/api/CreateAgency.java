package com.org.api;

import io.restassured.http.ContentType;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

@Ignore

public class CreateAgency extends CommonLogin{
	@Test(enabled = false)
	public void CreatesAgency() throws Exception {
		String jsessionId = response.cookie(JSESSIONID);
		String xsrfToken = response.cookie(XSRF_TOKEN);
		String CreateAgency = "src/test/resources/CreateAgency.json";
				response = given().
				body(Files.readAllBytes(Paths.get(CreateAgency))).
				when()
				.cookie(JSESSIONID,jsessionId)
				.cookie(XSRF_TOKEN,xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "agency/create");

		AssertJUnit.assertEquals(response.getStatusCode(), 201);

	}
}
