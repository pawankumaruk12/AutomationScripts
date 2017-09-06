package com.org.api;
//Tested and working on 31st March 2017
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import com.org.api.unittest.AccountsService;
@Ignore
// working fine on 31st Aug, but make sure last created account should not have children(company/project)
public class DeleteAccount extends CommonLogin {
	public static String ACCOUNT_ID;
	@Test(enabled = false)
	public void Delete_Accounts() throws Exception {


		String jsessionId =  response.cookie("JSESSIONID");
		String xsrfToken =  response.cookie("XSRF-TOKEN");
		ACCOUNT_ID = AccountsService.getLastAccountId(null, jsessionId, xsrfToken);

		System.out.println(ACCOUNT_ID);
		response = given().
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
						contentType(ContentType.JSON).
						post(API_PATH + "account/delete/" + ACCOUNT_ID);


		//System.out.println(response.getBody().asString());
		AssertJUnit.assertEquals( response.getStatusCode(), 200);



	}

}
