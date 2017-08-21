package com.org.api;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import com.org.api.unittest.ProjectDocumentService;

// Tested and working on 17th March 2017
public class ProjectDocumentById extends CommonLogin {
	public static String PROJECTDOCUMENT_ID;

	@Test
	public void ProjectDocumentByIds() throws Exception {
		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");

		PROJECTDOCUMENT_ID = ProjectDocumentService.getLastDocumentId(null,
				jsessionId, xsrfToken);
		System.out.println(PROJECTDOCUMENT_ID);
		resp = given().when().cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
				.post(API_PATH + "projectdocument/" + PROJECTDOCUMENT_ID);

		System.out.println(resp.getBody().asString());
		AssertJUnit.assertEquals(resp.getStatusCode(), 200);
		if (resp.getStatusCode() == 200) {
			System.out.println("API is working fine");
			System.out.println(resp.getStatusCode());
		} else {
			System.out.println("API is not working");
		}
	}

}
