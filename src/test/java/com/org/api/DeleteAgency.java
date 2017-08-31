package com.org.api;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import com.org.api.unittest.AgencyService;

//Tested and working on 14th march 2017 and agent should not associated with last created agency
@Ignore
public class DeleteAgency extends CommonLogin {
	public static String AGENCY_ID;

	@Test
	public void DeleteAgencys() throws Exception {
		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
		AGENCY_ID = AgencyService.getLastAgencyId(null, jsessionId, xsrfToken);
		resp = given().when().cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
				.post(API_PATH + "agency/delete/" + AGENCY_ID);
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