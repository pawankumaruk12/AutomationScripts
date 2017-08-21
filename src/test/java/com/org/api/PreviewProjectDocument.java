package com.org.api;

import static io.restassured.RestAssured.given;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

// Tested and working fine on 17th March 2017
public class PreviewProjectDocument extends CommonLogin {
	@Test
	public void PreviewProjectDocuments() throws Exception {
		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");

		resp = given()
				.when()
				.cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken)
				.get(API_PATH
						+ "projectdocument/dB2IpHFeTL86I9wLRoOysg/page/31/width/1400/preview/png");

		// System.out.println(resp.getBody().asString()); This line generates
		// junk codes for png file
		AssertJUnit.assertEquals(resp.getStatusCode(), 200);
		if (resp.getStatusCode() == 200) {
			System.out.println("API is working fine");
			System.out.println(resp.getStatusCode());
		} else {
			System.out.println("API is not working fine");

		}
	}
}