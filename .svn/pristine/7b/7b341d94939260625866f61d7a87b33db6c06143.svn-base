package com.org.api;

import io.restassured.http.ContentType;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
// need to form request json and test it, could not find this api in crewstart
public class AssignAgentToProjectMember extends CommonLogin {
	@Test
	public void AssignAgentToProjectMembers() throws Exception {
		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");

		resp = given()
				.body(Files.readAllBytes(Paths
						.get("src/test/resources/AssignAgent.json"))).when()
				.cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
				.post(API_PATH + "projectmember/assignagent");

		if (resp.getStatusCode() == 200) {
			System.out.println("API is working fine");
			System.out.println(resp.getStatusCode());
		} else {
			System.out.println("API is not working fine");

		}

	}
}