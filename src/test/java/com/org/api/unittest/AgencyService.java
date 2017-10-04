package com.org.api.unittest;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.CommonLogin;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

	public class AgencyService extends CommonLogin{
		public static String AGENCY_ID;

		public static String getLastAgencyId(String accountId, String jsessionId, String xsrfToken) throws Exception {
    	   String ListAgencies = "src/test/resources/ListAgenciesByPagination.json";
			
			Response  resp = given().
					
					body(Files.readAllBytes(Paths.get(ListAgencies)))
					.when()
					.cookie(JSESSIONID,jsessionId)
					.cookie(XSRF_TOKEN,xsrfToken).
					contentType(ContentType.JSON).
					post(CommonLogin.API_PATH + "agency/list");
			
		
			JsonParser parser = new JsonParser();
			JsonObject fullBody = parser.parse(resp.getBody().asString()).getAsJsonObject();
		
			return fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().get("id").getAsString();
			
		}
		
	}


