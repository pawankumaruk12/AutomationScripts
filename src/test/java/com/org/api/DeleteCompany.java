package com.org.api;

import com.org.api.model.Repository;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;


public class DeleteCompany extends CommonLogin{
	@Test
	public void testDeleteCompanys() throws Exception {
		String companyId = (String) Repository.getValue("companyId");

		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");

		response = given().
				when()
				.cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).
						contentType(ContentType.JSON).
						post(API_PATH + "company/delete/" +companyId).
						then().
						assertThat().statusCode(200).and().extract().response();


	}
	
				
	}


