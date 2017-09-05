package com.org.api;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import com.org.api.unittest.ProjectDocumentService;

// Tested and working on 17th March 2017
	public class DeleteProjectDocument extends CommonLogin{
		public static String PROJECTDOCUMENT_ID;
		@Test(enabled = false)
		public void DeleteProjectDocuments() throws Exception {
			String jsessionId = response.cookie("JSESSIONID");
			String xsrfToken = response.cookie("XSRF-TOKEN");
	
			 PROJECTDOCUMENT_ID = ProjectDocumentService.getLastDocumentId(null, jsessionId, xsrfToken);	
			 System.out.println(PROJECTDOCUMENT_ID);
					response = given().
					when()
					.cookie("JSESSIONID",jsessionId)
					.cookie("XSRF-TOKEN",xsrfToken).
					contentType(ContentType.JSON).
					post(API_PATH + "projectdocument/delete/" + PROJECTDOCUMENT_ID );
			System.out.println(response.getBody().asString());
			AssertJUnit.assertEquals(response.getStatusCode(), 200);
			if (response.getStatusCode() == 200) {
				System.out.println("API is working fine");
				System.out.println(response.getStatusCode());
			}
			else {
				System.out.println("API is not working");
			}	
		}

	}