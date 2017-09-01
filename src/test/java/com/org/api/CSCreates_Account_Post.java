package com.org.api;

//Tested and working on 31st March 2017.. everytime update the account name in request payload json

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import io.restassured.response.Response;

import java.nio.file.Files;
import java.nio.file.Paths;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.Assert;
import org.testng.annotations.Test;

@Ignore
public class CSCreates_Account_Post extends CommonLogin{
@Test(enabled = false)
	//@Test(priority=1)(enabled = false)
	public void Create_Account() throws Exception {

		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
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


		//System.out.println(resp.getBody().asString());
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

		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
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

		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
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
		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
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
