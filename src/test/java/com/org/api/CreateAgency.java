package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
//Tested and working on 14th March 2017, but change the agency name everytime
@Ignore

public class CreateAgency extends CommonLogin{
	@Test(enabled = false)
	public void CreatesAgency() throws Exception {
		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");
		String CreateAgency = "src/test/resources/CreateAgency.json";
				response = given().
				body(Files.readAllBytes(Paths.get(CreateAgency))).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "agency/create");
		//System.out.println(response.getBody().asString());
		AssertJUnit.assertEquals(response.getStatusCode(), 201);

	}

}
