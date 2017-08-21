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
		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
		PERSON_ID = PersonService.getLastPersonId(null, jsessionId, xsrfToken);
		// String PersonListNoAgent =
		// "src/test/resources/PersonListNoAgent.json";
		resp = given()
				.
				// body(Files.readAllBytes(Paths.get(PersonListNoAgent))).
				when().cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
				.post(API_PATH + "person/" + PERSON_ID);
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
