package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

//need to test ..pending
public class ListProjectDocumentbyPagination extends CommonLogin {
	@Test
	public void ListProjectDocuments() throws Exception {

		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");

		resp = given()
				.body(Files.readAllBytes(Paths
						.get("src/test/resources/ListbyProjectDoc.json")))
				.when().cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
				.post(API_PATH + "projectdocument/list");
		System.out.println(resp.getBody().asString());
		AssertJUnit.assertEquals(resp.statusCode(), 200);

		if (resp.getStatusCode() == 200) {
			System.out.println("API is working fine");
			System.out.println(resp.getStatusCode());
		} else {
			System.out.println("API is not working fine");
		}

	}
}
