package com.org.api.unittest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.CommonLogin;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
public class ProjectMemberService extends CommonLogin{
	//@Test
	public static String getLastProjectMemberId(String accountId, String jsessionId, String xsrfToken) throws Exception {
	   String ProjectMemberList = "src/test/resources/ProjectMemberList.json";
		
	Response resp = given().
				
				body(Files.readAllBytes(Paths.get(ProjectMemberList)))
				.when()
				.cookie(JSESSIONID,jsessionId)
				.cookie(XSRF_TOKEN,xsrfToken).
				contentType(ContentType.JSON).
				post(CommonLogin.API_PATH + "projectmember/list");

		JsonParser parser = new JsonParser();
		JsonObject fullBody = parser.parse(resp.getBody().asString()).getAsJsonObject();
	
		return fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("projectMember").get("id").getAsString();
		
	}
	
}
