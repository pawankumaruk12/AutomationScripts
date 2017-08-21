package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.org.api.unittest.ProjectMemberService;

// Tested on 22nd March 2017 and working fine
public class ProjectMemberById extends CommonLogin {

	@Test
	public void ListProjectMemberForCurrentUserByPaginations() throws Exception {

		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
		String ProjectMemberId = ProjectMemberService.getLastProjectMemberId(
				null, jsessionId, xsrfToken);
		resp = given()
				.body(Files.readAllBytes(Paths
						.get("src/test/resources/ListbyProjectDoc.json")))
				.when().cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
				.post(API_PATH + "projectmember/" + ProjectMemberId);
		System.out.println(resp.getBody().asString());
		AssertJUnit.assertEquals(resp.statusCode(), 200);

		if (resp.getStatusCode() == 200) {
			System.out.println("API is working fine");
			System.out.println(resp.getStatusCode());
			System.out.println(ProjectMemberId);

		} else {
			System.out.println("API is not working fine");
		}

	}

}
