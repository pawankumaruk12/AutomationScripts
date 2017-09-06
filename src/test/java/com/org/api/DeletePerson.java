package com.org.api;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import com.org.api.unittest.PersonService;
@Ignore
//This API is not implemented in CrewStart
// Tested on 13th Mar 2017 and working
	public class DeletePerson extends CommonLogin{
		public static String PERSON_ID;
		@Test(enabled = false)
		public void DeletePersons() throws Exception {
			String jsessionId = response.cookie("JSESSIONID");
			String xsrfToken = response.cookie("XSRF-TOKEN");
	
			 PERSON_ID = PersonService.getLastPersonId(null, jsessionId, xsrfToken);	
			 System.out.println(PERSON_ID);
					response = given().
					when()
					.cookie("JSESSIONID",jsessionId)
					.cookie("XSRF-TOKEN",xsrfToken).
					contentType(ContentType.JSON).
					post(API_PATH + "person/delete/" + PERSON_ID );
			//System.out.println(response.getBody().asString());
			AssertJUnit.assertEquals(response.getStatusCode(), 200);

		}

	}


