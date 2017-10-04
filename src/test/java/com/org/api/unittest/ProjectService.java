package com.org.api.unittest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.CommonLogin;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ProjectService extends CommonLogin{

	public static String getLastProjectId(String accountId, String jsessionId, String xsrfToken) throws Exception {
		String projectListPayloadFilePath = "src/test/resources/project_list_payload.json";
		Response  resp = given()
				.when()
				.body(Files.readAllBytes(Paths.get(projectListPayloadFilePath)))
				.cookie(JSESSIONID,jsessionId)
				.cookie(XSRF_TOKEN,xsrfToken).
				contentType(ContentType.JSON).
				post(CommonLogin.API_PATH + "project/list");
	
		JsonParser parser = new JsonParser();
		JsonObject fullBody = parser.parse(resp.getBody().asString()).getAsJsonObject();
		return fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("project").get("id").getAsString();
	}
	
}
