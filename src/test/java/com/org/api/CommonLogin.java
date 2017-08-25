package com.org.api;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.BeforeClass;

public abstract class CommonLogin {

	private static final String PATH = "src/test/resources/login.json";
	private static final String PATH_LOGIN_HOD = "src/test/resources/login_hod.json";
	private static final String PATH_LOGIN_PM = "src/test/resources/login_hod.json";
	private static final String PATH_LOGIN_TM = "src/test/resources/login_hod.json";
	private static final String PATH_LOGIN_AGENT = "src/test/resources/login_hod.json";

	protected Response resp = null;

	public static final String API_PATH = "http://192.168.56.139:8080/sdw/rest/";

	@BeforeClass
	public void login() throws IOException{


		resp = given().


				body(Files.readAllBytes(Paths.get(PATH))).
				when().
				contentType(ContentType.JSON).

				post("http://192.168.56.139:8080/sdw/rest/authentication/login");

		System.out.println(resp.asString());
		System.out.println(resp.getStatusCode());

	}

	protected void loginAsHOD() throws Exception {
		given().
				//		body(Files.readAllBytes(Paths.get(PATH))).
						when().
				contentType(ContentType.JSON).
				post("http://192.168.56.139:8080/sdw/rest/authentication/logout");

		given().
				body(Files.readAllBytes(Paths.get(PATH_LOGIN_HOD))).
				when().
				contentType(ContentType.JSON).
				post("http://192.168.56.139:8080/sdw/rest/authentication/login");
	}


	protected void loginAsProjectManager() throws Exception {
		given().
				//		body(Files.readAllBytes(Paths.get(PATH))).
						when().
				contentType(ContentType.JSON).
				post("http://192.168.56.139:8080/sdw/rest/authentication/logout");

		given().
				body(Files.readAllBytes(Paths.get(PATH_LOGIN_PM))).
				when().
				contentType(ContentType.JSON).
				post("http://192.168.56.139:8080/sdw/rest/authentication/login");
	}
	protected void loginAsTeamMember() throws Exception {
		given().
				//		body(Files.readAllBytes(Paths.get(PATH))).
						when().
				contentType(ContentType.JSON).
				post("http://192.168.56.139:8080/sdw/rest/authentication/logout");

		given().
				body(Files.readAllBytes(Paths.get(PATH_LOGIN_TM))).
				when().
				contentType(ContentType.JSON).
				post("http://192.168.56.139:8080/sdw/rest/authentication/login");
	}

	protected void loginAsAgent() throws Exception {
		given().
				//		body(Files.readAllBytes(Paths.get(PATH))).
						when().
				contentType(ContentType.JSON).
				post("http://192.168.56.139:8080/sdw/rest/authentication/logout");

		given().
				body(Files.readAllBytes(Paths.get(PATH_LOGIN_AGENT))).
				when().
				contentType(ContentType.JSON).
				post("http://192.168.56.139:8080/sdw/rest/authentication/login");
	}
}
