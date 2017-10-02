package com.org.api.unittest;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.CommonLogin;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static com.org.api.CommonLogin.JSESSIONID;
import static com.org.api.CommonLogin.XSRF_TOKEN;
import static io.restassured.RestAssured.given;

	public class PersonService {
		public static String PERSON_ID;
       @Test
		public static String getLastPersonId(String accountId, String jsessionId, String xsrfToken) throws Exception {
    	   String PersonListNoAgent = "src/test/resources/PersonListNoAgent.json";
			
			Response  resp = given().
					
					body(Files.readAllBytes(Paths.get(PersonListNoAgent)))
					.when()
					.cookie(JSESSIONID,jsessionId)
					.cookie(XSRF_TOKEN,xsrfToken).
					contentType(ContentType.JSON).
					post(CommonLogin.API_PATH + "person/list");
			
		
			JsonParser parser = new JsonParser();
			JsonObject fullBody = parser.parse(resp.getBody().asString()).getAsJsonObject();
		
			//return fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("person").get("id").getAsString();
			return fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() -1).getAsJsonObject().getAsJsonObject("person").get("id").getAsString();
		}
		
	}


