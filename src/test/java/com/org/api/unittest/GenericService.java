package com.org.api.unittest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.CommonLogin;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.nio.file.Files;
import java.nio.file.Paths;

import static com.org.api.CommonLogin.JSESSIONID;
import static com.org.api.CommonLogin.XSRF_TOKEN;
import static io.restassured.RestAssured.given;

public class GenericService {

	private String jsessionId;
	private String xsrfToken;

	private static final String LIST_PATH = "/list";

	public GenericService(String jsessionId, String xsrfToken) {
		this.jsessionId = jsessionId;
		this.xsrfToken = xsrfToken;
	}

	public String getLastIdFromList(String filePath, String entity)
			throws Exception {
		if (this.jsessionId == null || this.xsrfToken == null)
			throw new Exception("No cookies specified");
		// String departmentListPayloadFilePath =
		// "src/test/resources/department_list_payload.json";
		Response resp = given().when()
				.body(Files.readAllBytes(Paths.get(filePath)))
				.cookie(JSESSIONID, this, jsessionId)
				.cookie(XSRF_TOKEN, this.xsrfToken)
				.contentType(ContentType.JSON)
				.post(CommonLogin.API_PATH + entity + LIST_PATH);

		JsonParser parser = new JsonParser();
		JsonObject fullBody = parser.parse(resp.getBody().asString())
				.getAsJsonObject();

		return fullBody.get("results").getAsJsonArray()
				.get(fullBody.get("results").getAsJsonArray().size() - 1)
				.getAsJsonObject().getAsJsonObject(entity).get("id")
				.getAsString();
	}

	public Integer getNextIdFromList(String filePath, String entity)
			throws Exception {
		if (this.jsessionId == null || this.xsrfToken == null)
			throw new Exception("No cookies specified");

		Response resp = given().when()
				.body(Files.readAllBytes(Paths.get(filePath)))
				.cookie(JSESSIONID, this, jsessionId)
				.cookie(XSRF_TOKEN, this.xsrfToken)
				.contentType(ContentType.JSON)
				.post(CommonLogin.API_PATH + entity + LIST_PATH);

		JsonParser parser = new JsonParser();
		JsonObject fullBody = parser.parse(resp.getBody().asString())
				.getAsJsonObject();

		String name = fullBody.get("results").getAsJsonArray()
				.get(fullBody.get("results").getAsJsonArray().size() - 1)
				.getAsJsonObject().getAsJsonObject(entity).get(entity + "Name")
				.getAsString();
		return getNextIdFromName(entity, name);
	}

	public Integer getNextIdFromName(String entity, String name) {
		Integer existingId = 1;
		if (name == null || name.isEmpty() || entity == null
				|| !name.toLowerCase().startsWith(entity.toLowerCase())) {
			return existingId;
		}

		String id = name.toLowerCase().substring(entity.toLowerCase().length());
		try {
			existingId = Integer.parseInt(id);
		} catch (NumberFormatException ex) {
			return existingId;
		}
		return existingId + 1;
	}
}
