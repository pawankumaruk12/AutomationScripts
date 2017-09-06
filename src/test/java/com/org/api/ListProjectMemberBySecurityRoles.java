package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
//Need to Fix the issue dynamic json data
public class ListProjectMemberBySecurityRoles extends CommonLogin {
	@Test(enabled = false)
	public void ListProjectMemberSecurityRoles() throws Exception {

		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");

		response = given()
				.body(Files.readAllBytes(Paths
						.get("src/test/resources/ListbySecurityRoles.json")))
				.when().cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).

				contentType(ContentType.JSON)
				.post(API_PATH + "projectMember/list/bysecurityroles");
		//System.out.println(response.getBody().asString());
		AssertJUnit.assertEquals(response.statusCode(), 200);



	}
}
