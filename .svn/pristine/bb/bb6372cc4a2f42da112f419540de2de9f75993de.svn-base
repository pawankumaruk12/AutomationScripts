package com.org.api.unittest;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.CommonLogin;

public class ProjectService {

	public static String getLastProjectId(String accountId, String jsessionId, String xsrfToken) throws Exception {
		String projectListPayloadFilePath = "src/test/resources/project_list_payload.json";
		
		Response  resp = given()
				.when()
				.body(Files.readAllBytes(Paths.get(projectListPayloadFilePath)))
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
				contentType(ContentType.JSON).
				post(CommonLogin.API_PATH + "project/list");
		
	
		JsonParser parser = new JsonParser();
		JsonObject fullBody = parser.parse(resp.getBody().asString()).getAsJsonObject();
	
	
		return fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("project").get("id").getAsString();
	}
	
}
