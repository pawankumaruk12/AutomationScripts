package com.org.api;



import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import static io.restassured.RestAssured.given;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Company;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

// working on 31st Aug, but change the company name on json
public class CreateCompany extends CommonLogin {

	@BeforeClass
	public void init(){
		System.out.println("===== Starting CreateCompany Test =====");
	}

	@Test
	public void testCreateCompany(){
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

		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");

		Response createdResponse = given().
				body(json).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
						contentType(ContentType.JSON).
						post(API_PATH + "company/create");

		JsonParser parser = new JsonParser();
		JsonObject fullBody = parser.parse(createdResponse.getBody().asString()).getAsJsonObject();

		String companyId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("company").get("id").getAsString();
		Repository.addData("companyId", companyId);
		String name = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("company").get("name").getAsString();
		Repository.addData("name",name);
		String registeredNumber = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("company").get("registeredNumber").getAsString();
		Repository.addData("registeredNumber", registeredNumber);
		String vatNumber = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("company").get("vatNumber").getAsString();
		Repository.addData("vatNumber",vatNumber);
		String vatType = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("company").get("vatType").getAsString();
		Repository.addData("vatType",vatType);
		String sdCompanyId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("company").get("sdCompanyId").getAsString();
		Repository.addData("sdCompanyId",sdCompanyId);

	}



	//@Test
	public void Create_Companys() throws Exception{

		String accountId = (String) Repository.getValue("accountId");

		System.out.println("Account id ============ " + accountId);
	
		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");
		String createCompanyJson = "src/test/resources/CreateCompany.json";
		
		response = given().
		body(Files.readAllBytes(Paths.get(createCompanyJson))).
		when()
		.cookie("JSESSIONID",jsessionId)
		.cookie("XSRF-TOKEN",xsrfToken).
		contentType(ContentType.JSON).
		post(API_PATH + "company/create");
		
		System.out.println(response.getBody().asString());
		AssertJUnit.assertEquals(response.getStatusCode(), 201);

		Assert.assertTrue(response.getStatusCode()==201, "API is NOT working fine");
		//Assert.assertEquals( response.getStatusCode(), 400, "API is NOT working fine 2");
		//Assert.assertFalse(response.getStatusCode()==400, "API is NOT working fine");

	
		
	
	}

}
