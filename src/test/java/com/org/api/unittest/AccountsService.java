package com.org.api.unittest;


import com.org.api.CommonLogin;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class AccountsService extends CommonLogin{
	public static String ACCOUNT_ID;
	// @Test
	public static String getLastAccountId(String accountId, String jsessionId, String xsrfToken) throws Exception {
		String AccountList = "src/test/resources/ListByAccounts.json";


		Response res = given().

				body(Files.readAllBytes(Paths.get(AccountList)))
				.when()
				.cookie(JSESSIONID,jsessionId)
				.cookie(XSRF_TOKEN,xsrfToken).
						contentType(ContentType.JSON).
				//post(CommonLogin.API_PATH + "account/list");
						post("http://192.168.56.139:8080/sdw/rest/account/list").
						then().assertThat().statusCode(200).and().extract().response();

		String responseString = res.asString();
		JsonPath js = new JsonPath(responseString);
		ACCOUNT_ID = js.get("results[0].account.id");
		return ACCOUNT_ID;

//			AssertJUnit.assertEquals(response.getStatusCode(), 200);
//			JsonParser parser = new JsonParser();
//			JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();
//			ACCOUNT_ID = parser.parse(response.getBody().asString()).getAsString();
//			System.out.println(ACCOUNT_ID);
//
//			return fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("account").get("id").getAsString();
//
	}

}



