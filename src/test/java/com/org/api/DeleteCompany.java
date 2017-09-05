package com.org.api;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
@Ignore
//Need to implement to get dynamic company id from company service
public class DeleteCompany extends CommonLogin{
	@Test(enabled = false)
	public void DeleteCompanys() throws Exception {
		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");
		
		response = given().
				when()
				.cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "company/delete/oXTZf4xofVB3Mc7IDpVvPg");
		
				System.out.println(response.getBody().asString());
				AssertJUnit.assertEquals(response.getStatusCode(), 200);
				if (response.getStatusCode() == 200){
					System.out.println("API is working fine");
					System.out.println(response.getStatusCode());
				}
				else
					System.out.println("API is not working fine");
				}
	
				
	}


