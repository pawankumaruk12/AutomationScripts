package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

//Tested and working on 31st March 2017
public class ListProjectDocumentbyPagination extends CommonLogin {
	@Test
	public void ListProjectDocuments() throws Exception {

		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");

		response = given()
				.body(Files.readAllBytes(Paths
						.get("src/test/resources/ListbyProjectDoc.json")))
				.when().cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
				.post(API_PATH + "projectdocument/list");
		//System.out.println(response.getBody().asString());
		AssertJUnit.assertEquals(response.statusCode(), 200);

	}
}
