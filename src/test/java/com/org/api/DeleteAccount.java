package com.org.api;
//Tested and working on 31st March 2017
import com.org.api.model.Repository;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import com.org.api.unittest.AccountsService;

public class DeleteAccount extends CommonLogin {

	@Test

	public void testDeleteAccount() throws Exception {

		String accountId = (String) Repository.getValue("accountId");

		String jsessionId =  response.cookie("JSESSIONID");
		String xsrfToken =  response.cookie("XSRF-TOKEN");


		response = given().
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
						contentType(ContentType.JSON).
						post(API_PATH + "account/delete/" + accountId ).
				then().
				assertThat().statusCode(200).and().extract().response();



		//System.out.println(response.getBody().asString());
		AssertJUnit.assertEquals( response.getStatusCode(), 200);



	}

}
