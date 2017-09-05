package com.org.api;

import static io.restassured.RestAssured.given;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

// Tested and working fine on 17th March 2017
public class PreviewProjectDocument extends CommonLogin {
	@Test
	public void PreviewProjectDocuments() throws Exception {
		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");

		response = given()
				.when()
				.cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken)
				.get(API_PATH
						+ "projectdocument/dB2IpHFeTL86I9wLRoOysg/page/31/width/1400/preview/png");

		// System.out.println(response.getBody().asString()); This line generates
		// junk codes for png file
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		if (response.getStatusCode() == 200) {
			System.out.println("API is working fine");
			System.out.println(response.getStatusCode());
		} else {
			System.out.println("API is not working fine");

		}
	}
}