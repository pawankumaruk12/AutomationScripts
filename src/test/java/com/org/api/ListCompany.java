package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
//working 6th sep, chaged the payload in json

public class ListCompany extends CommonLogin {

	@Test
	public void ListCompanys() throws Exception {

		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");
		response = given()
				.body(Files.readAllBytes(Paths
						.get("src/test/resources/ListbyCompany.json"))).when()
				.cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
				.post(API_PATH + "company/list");

		//System.out.println(response.getBody().asString());
		//System.out.println(response.body().prettyPrint());
		//System.out.println(response.body().prettyPeek());
		AssertJUnit.assertEquals(response.getStatusCode(), 200);

	}

}
