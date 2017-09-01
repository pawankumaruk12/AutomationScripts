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
public class Delete_Account extends CommonLogin {
	public static String ACCOUNT_ID;
	@Test(enabled = false)
	public void Delete_Accounts() throws Exception {


		String jsessionId =  resp.cookie("JSESSIONID");
		String xsrfToken =  resp.cookie("XSRF-TOKEN");
		ACCOUNT_ID = AccountsService.getLastAccountId(null, jsessionId, xsrfToken);

		System.out.println(ACCOUNT_ID);
		resp = given().
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
						contentType(ContentType.JSON).
						post(API_PATH + "account/delete/" + ACCOUNT_ID);


		System.out.println(resp.getBody().asString());
		AssertJUnit.assertEquals( resp.getStatusCode(), 200);
		if (resp.getStatusCode()==200){
			System.out.println("API is working fine");
			System.out.println(resp.getStatusCode());
		}
		else {
			System.out.println("API is not working fine");

		}


	}

}
