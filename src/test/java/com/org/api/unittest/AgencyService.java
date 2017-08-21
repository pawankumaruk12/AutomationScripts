package com.org.api.unittest;


import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.CommonLogin;

	public class AgencyService {
		public static String AGENCY_ID;

		public static String getLastAgencyId(String accountId, String jsessionId, String xsrfToken) throws Exception {
    	   String ListAgencies = "src/test/resources/ListAgenciesByPagination.json";
			
			Response  resp = given().
					
					body(Files.readAllBytes(Paths.get(ListAgencies)))
					.when()
					.cookie("JSESSIONID",jsessionId)
					.cookie("XSRF-TOKEN",xsrfToken).
					contentType(ContentType.JSON).
					post(CommonLogin.API_PATH + "agency/list");
			
		
			JsonParser parser = new JsonParser();
			JsonObject fullBody = parser.parse(resp.getBody().asString()).getAsJsonObject();
		
			return fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().get("id").getAsString();
			
		}
		
	}


