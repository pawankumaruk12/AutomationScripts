package com.org.api;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import com.org.api.unittest.PersonService;

//Tested and working on 14th March 2017
public class PersonById extends CommonLogin {
	public static String PERSON_ID;

	@Test
	public void PersonByIds() throws Exception {
		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");
		PERSON_ID = PersonService.getLastPersonId(null, jsessionId, xsrfToken);
		// String PersonListNoAgent =
		// "src/test/resources/PersonListNoAgent.json";
		response = given()
				.
				// body(Files.readAllBytes(Paths.get(PersonListNoAgent))).
				when().cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
				.post(API_PATH + "person/" + PERSON_ID);
		//System.out.println(response.getBody().asString());
		AssertJUnit.assertEquals(response.getStatusCode(), 200);

	}

}
