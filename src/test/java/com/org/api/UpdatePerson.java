package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.Assert;
import org.testng.annotations.Test;
// Version id need to updated in request json
@Ignore
public class UpdatePerson extends CommonLogin {
	@Test(enabled = false)
	public void UpdatePersons() throws Exception {
		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");

		response = given()
				.body(Files.readAllBytes(Paths
						.get("src/test/resources/UpdatePerson.json"))).
						when()
				.cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).
				contentType(ContentType.JSON)
				.post(API_PATH + "person/update");
		
		//System.out.println(response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 200);


	}

}
