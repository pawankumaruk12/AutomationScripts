package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
//working on 1st Sep
public class ListDepartment extends CommonLogin {

	@Test
	public void ListDepartments() throws Exception {

		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");

		response = given()
				.body(Files.readAllBytes(Paths
						.get("src/test/resources/ListbyDepartment.json")))
				.when().cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
				.post(API_PATH + "department/list");

		System.out.println(response.getBody().asString());
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		if (response.getStatusCode() == 200) {
			System.out.println("API is working fine");
			System.out.println(response.getStatusCode());

		} else {
			System.out.println("API is not working fine");
		}

	}
}
