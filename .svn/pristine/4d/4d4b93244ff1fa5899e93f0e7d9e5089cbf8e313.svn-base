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
		String jsessionId = resp.cookie("JSESSION");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
		resp = given()
				.body(Files.readAllBytes(Paths
						.get("src/test/resources/Logout.json"))).when()
				.cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
				.post(API_PATH + "authentication/logout");

		System.out.println(resp.getBody().asString());
		AssertJUnit.assertEquals(resp.getStatusCode(), 200);

		if (resp.getStatusCode() == 200) {
			System.out.println("API is working fine");
			System.out.println(resp.getStatusCode());
		} else {
			System.out.println("API is not working fine");

		}

	}

}
