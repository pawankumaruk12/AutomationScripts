package com.org.api;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
//Tested and worked on 31st March 2017
public class ProjectType extends CommonLogin {
	@Test
	public void ProjectTypes() throws Exception {
		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");

		response = given().
				body(Files.readAllBytes(Paths.get("src/test/resources/ProjectType.json"))).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).
						contentType(ContentType.JSON).
						post(API_PATH + "project/types");

		System.out.println(response.getBody().asString());
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		if (response.getStatusCode()==200) {
			System.out.println("API is working fine");
			System.out.println(response.getStatusCode());
		}
		else
		{
			System.out.println("API is not working fine");
		}

	}

}
