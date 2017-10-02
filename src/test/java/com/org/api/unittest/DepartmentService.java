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

	public class DepartmentService {
		public static String DEPARTMENT_ID;
       @Test
		public static String getLastDepartmentId(String projectId, String jsessionId, String xsrfToken) throws Exception {
			String departmentListPayloadFilePath = "src/test/resources/departmentlistpayload.json";
			
			Response  resp = given()
					.when()
					.body(Files.readAllBytes(Paths.get(departmentListPayloadFilePath)))
					.cookie(JSESSIONID,jsessionId)
					.cookie(XSRF_TOKEN,xsrfToken).
					contentType(ContentType.JSON).
					post(CommonLogin.API_PATH + "department/list");
			
		
			JsonParser parser = new JsonParser();
			JsonObject fullBody = parser.parse(resp.getBody().asString()).getAsJsonObject();
		
			return fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("department").get("id").getAsString();
		}
		
	}

