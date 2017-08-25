package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import org.testng.Assert;
import org.testng.annotations.Test;

// This tested and working on 17th March 2017
public class UpdatesProjectDocument extends CommonLogin {
	@Test
	public void UpdatesProjectDocuments() throws Exception {
		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");

		resp = given()
				.body(Files.readAllBytes(Paths
						.get("src/test/resources/UpdateProjectDocument.json")))
				.when().cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
				.post(API_PATH + "projectdocument/update");
		System.out.println(resp.getBody().asString());
		Assert.assertEquals(resp.getStatusCode(), 200);
		if (resp.getStatusCode() == 200) {
			System.out.println("API is working fine");
			System.out.println(resp.getStatusCode());
		} else {
			System.out.println("API is not working fine");

		}

	}

}
