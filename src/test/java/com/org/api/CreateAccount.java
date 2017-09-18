package com.org.api;

//Tested and working on 31st March 2017..

import static io.restassured.RestAssured.given;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Account;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import io.restassured.response.Response;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.naming.Name;

public class CreateAccount extends CommonLogin{

	@BeforeClass
	public void init(){
		System.out.println("=====Starting CreateAccount Test=====");
	}

	@Test
	public void testAccountCreation(){


		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");

		Account account = new Account();
		account.setName("AutoAccount" + new Date());
		account.setDescription("Automation Account");
		account.setTypeId(2);
		account.setAccountId(1);

		Gson gson = new Gson();
		String json = gson.toJson(account);

		Response createResponse = given().
				body(json).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
						contentType(ContentType.JSON).
						post(API_PATH + "account/create").then()
				.assertThat().statusCode(201).and().extract().response();


		JsonParser parser = new JsonParser();
		JsonObject fullBody = parser.parse(createResponse.getBody().asString()).getAsJsonObject();

		String accountId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("account").get("id").getAsString();
		Repository.addData("accountId", accountId);
		String name = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("account").get("name").getAsString();
		Repository.addData("name",name);
		String description = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("account").get("description").getAsString();
		Repository.addData("description",description);
		String stringTypeId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("account").get("typeId").getAsString();
		Repository.addData("stringTypeId",stringTypeId);





	}

//@Test
	@Test(enabled = false)
	public void Create_Account() throws Exception {

		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");
		String createAcc_json_path = "src/test/resources/createAccount.json";

		Response res = given().
				body(Files.readAllBytes(Paths.get(createAcc_json_path))).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
						contentType(ContentType.JSON).
				//post(loginapiPath);
						post(API_PATH + "account/create").then()
				.assertThat().statusCode(201).and().extract().response();

		String responseString = res.asString();
		System.out.println(res.body().asString());

		JsonPath js = new JsonPath(responseString);
		String AccountId = js.get("results[0].account.id");
		System.out.println(AccountId);


		//System.out.println(response.getBody().asString());
		Assert.assertEquals( res.getStatusCode(), 201);
		if (res.getStatusCode()==201){
			System.out.println("API is working fine");
			System.out.println(res.getStatusCode());
		}
		else {
			System.out.println("API is not working fine");

		}


	}
	@Test(enabled = false)

	//@Test(priority=2, dependsOnMethods = {"Create_Account"})
	public void CheckingDuplicateAccount() throws Exception {

		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");
		String createAcc_json_path = "src/test/resources/createAccount.json";

		Response res = given().
				body(Files.readAllBytes(Paths.get(createAcc_json_path))).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
						contentType(ContentType.JSON).
				//post(loginapiPath);
						post(API_PATH + "account/create").then()
				.assertThat().statusCode(500).and().extract().response();



		Assert.assertEquals( res.getStatusCode(), 500);
		if (res.getStatusCode()==500){
			System.out.println("API is working fine");
			System.out.println(res.getStatusCode());
		}
		else {
			System.out.println("API is not working fine");

		}


	}
	@Test(enabled = false)
//	@Test(priority=3, dependsOnMethods = {"CheckingDuplicateAccount"})
	public void CheckingBlankAccount() throws Exception {

		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");
		//String createAcc_json_path = "src/test/resources/createAccount.json";

		Response res = given().
				//body(Files.readAllBytes(Paths.get(createAcc_json_path))).
						when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
						contentType(ContentType.JSON).
				//post(loginapiPath);
						post(API_PATH + "account/create").then()
				.assertThat().statusCode(400).and().extract().response();



		Assert.assertEquals( res.getStatusCode(), 400);



	}
	@Test(enabled = false)
//	@Test(priority=4, dependsOnMethods = {"CheckingBlankAccount"})
	public void createAccountAsHOD() throws Exception {

		this.loginAsHOD();
		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");
		String createAcc_json_path = "src/test/resources/createAccount.json";
		//String createAcc_json_path = "src/test/resources/createAccount.json";

		Response res = given().
				body(Files.readAllBytes(Paths.get(createAcc_json_path))).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
						contentType(ContentType.JSON).
				//post(loginapiPath);
						post(API_PATH + "account/create").then()
				.assertThat().statusCode(401).and().extract().response();

		Assert.assertEquals( res.getStatusCode(), 401);

	}

}
