package com.org.api;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

// Tested and working fine on 17th March 2017
public class PreviewProjectDocument extends CommonLogin {
	@Test
	public void PreviewProjectDocuments() throws Exception {
		String jsessionId = response.cookie(JSESSIONID);
		String xsrfToken = response.cookie(XSRF_TOKEN);

		response = given()
				.when()
				.cookie(JSESSIONID, jsessionId)
				.cookie(XSRF_TOKEN, xsrfToken)
				.get(API_PATH
						+ "projectdocument/dB2IpHFeTL86I9wLRoOysg/page/31/width/1400/preview/png");
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
}