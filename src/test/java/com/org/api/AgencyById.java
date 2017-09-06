package com.org.api;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.org.api.unittest.AgencyService;

//Tested and working on 14th march 2017
public class AgencyById extends CommonLogin {
	public static String AGENCY_ID;

	@Test
	public void AgencyByIds() throws Exception {
		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");
		AGENCY_ID = AgencyService.getLastAgencyId(null, jsessionId, xsrfToken);
		response = given().when().cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
				.post(API_PATH + "agency/" + AGENCY_ID);
		System.out.println(response.getBody().asString());
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		/*if (response.getStatusCode() == 200) {
			System.out.println("API is working fine");
			System.out.println(response.getStatusCode());
		} else {
			System.out.println("API is not working");
		}*/
	}
}
