package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

// This forgot API is returning 200 even if we give wrong input
public class Logout extends CommonLogin {
	@Test
	public void Logouts() throws Exception {
		String jsessionId = response.cookie("JSESSION");
		String xsrfToken = response.cookie("XSRF-TOKEN");
		response = given()
				.body(Files.readAllBytes(Paths
						.get("src/test/resources/logout.json"))).when()
				.cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
				.post(API_PATH + "authentication/logout");

		//System.out.println(response.getBody().asString());
		AssertJUnit.assertEquals(response.getStatusCode(), 200);



	}

}
