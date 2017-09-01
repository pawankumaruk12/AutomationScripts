
//Need to understand bit more for this api

package com.org.api;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
@Ignore
// Tested and working fine on 17th March 2017
public class PreviewProjectDocumentFromTemplate extends CommonLogin {
	@Test(enabled = false)
	public void PreviewProjectDocumentsfromTemplate() throws Exception {
		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");

		resp = given()
				.when()
				.cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken)
				.get(API_PATH
						+ "projectdocument/template/9wkSlqHAU83iJnYFPj72xw/document/YnqeHtiAOucVRJpHukJYuw/page/1/width/1400/preview/png");

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
