package com.org.api;

import io.restassured.http.ContentType;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

// need to form request json and test it, could not find this api in crewstart
public class AssignAgentToProjectMember extends CommonLogin {
	@Test(enabled = false)
	public void AssignAgentToProjectMembers() throws Exception {
		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");

		response = given()
				.body(Files.readAllBytes(Paths
						.get("src/test/resources/AssignAgent.json"))).when()
				.cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
				.post(API_PATH + "projectmember/assignagent");



	}
}