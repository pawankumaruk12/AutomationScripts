package com.org.api;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Company;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;


public class CreateCompany extends CommonLogin {
	@Test
	public void testCreateCompany() {
		String accountId = (String) Repository.getValue("accountId");

		Company company = new Company();
		company.setAccountId(accountId);
		company.setName("AutoCompanyName" + new Date());
		company.setTypeId(1);
		company.setRegisteredNumber("reg001");
		company.setVatNumber("v001");
		company.setSdCompanyId("sd001");
		company.setVatType("V1");

		Gson gson = new Gson();
		String json = gson.toJson(company);

		String jsessionId = response.cookie(JSESSIONID);
		String xsrfToken = response.cookie(XSRF_TOKEN);

		Response createdResponse = given().
				body(json).
				when()
				.cookie(JSESSIONID, jsessionId)
				.cookie(XSRF_TOKEN, xsrfToken).
						contentType(ContentType.JSON).
						post(API_PATH + "company/create");

		JsonParser parser = new JsonParser();
		JsonObject fullBody = parser.parse(createdResponse.getBody().asString()).getAsJsonObject();

		String companyId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("company").get("id").getAsString();
		Repository.addData("companyId", companyId);
		String name = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("company").get("name").getAsString();
		Repository.addData("name", name);
		String registeredNumber = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("company").get("registeredNumber").getAsString();
		Repository.addData("registeredNumber", registeredNumber);
		String vatNumber = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("company").get("vatNumber").getAsString();
		Repository.addData("vatNumber", vatNumber);
		String vatType = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("company").get("vatType").getAsString();
		Repository.addData("vatType", vatType);
		String sdCompanyId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("company").get("sdCompanyId").getAsString();
		Repository.addData("sdCompanyId", sdCompanyId);
	}
}

