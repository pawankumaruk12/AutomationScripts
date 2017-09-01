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
		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
		
		resp = given().
				when()
				.cookie("JSESSIONID", jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "company/delete/oXTZf4xofVB3Mc7IDpVvPg");
		
				System.out.println(resp.getBody().asString());
				AssertJUnit.assertEquals(resp.getStatusCode(), 200);
				if (resp.getStatusCode() == 200){
					System.out.println("API is working fine");
					System.out.println(resp.getStatusCode());
				}
				else
					System.out.println("API is not working fine");
				}
	
				
	}


