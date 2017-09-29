package com.org.api;

import static io.restassured.RestAssured.given;

import com.google.gson.Gson;
import com.org.api.model.NewUser;
import com.org.api.model.Repository;
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
	private static final String PATH_LOGIN_TM = "src/test/resources/login_tm.json";
	private static final String PATH_LOGIN_AGENT = "src/test/resources/login_hod.json";

	protected Response response = null;

	public static final String API_PATH = "http://192.168.56.139:8080/sdw/rest/";

	@BeforeClass
	public void login() throws IOException{


		response = given().


				body(Files.readAllBytes(Paths.get(PATH))).
				when().
				contentType(ContentType.JSON).

				post("http://192.168.56.139:8080/sdw/rest/authentication/login");

//		System.out.println(response.asString());
//		System.out.println(response.getStatusCode());

	}

	protected void loginAsHOD() throws Exception {
		response = given().
				//		body(Files.readAllBytes(Paths.get(PATH))).
						when().
				contentType(ContentType.JSON).
				post("http://192.168.56.139:8080/sdw/rest/authentication/logout");

		response = given().
				body(Files.readAllBytes(Paths.get(PATH_LOGIN_HOD))).
				when().
				contentType(ContentType.JSON).
				post("http://192.168.56.139:8080/sdw/rest/authentication/login");
	}

	protected void loginAsProjectManager() throws Exception {
		response =	given().
				//		body(Files.readAllBytes(Paths.get(PATH))).
						when().
				contentType(ContentType.JSON).
				post("http://192.168.56.139:8080/sdw/rest/authentication/logout");

		response = given().
				body(Files.readAllBytes(Paths.get(PATH_LOGIN_PM))).
				when().
				contentType(ContentType.JSON).
				post("http://192.168.56.139:8080/sdw/rest/authentication/login");
	}
	protected void loginAsTeamMember() throws Exception {
		String username = (String) Repository.getValue("username");
		String password = (String) Repository.getValue("password");

		String jsessionId  = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");

		NewUser newUser = new NewUser();
		newUser.setUsername(username);
		newUser.setPassword(password);

		Gson gson = new Gson();
		String json = gson.toJson(newUser);

		response = given().
						//body(json).
						when().

				cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).
				contentType(ContentType.JSON).
				post("http://192.168.56.139:8080/sdw/rest/authentication/logout");

		response = given().
				body(json).
				when().
				contentType(ContentType.JSON).
				post("http://192.168.56.139:8080/sdw/rest/authentication/login");
	}


	protected void loginAsAgent() throws Exception {
		response = given().
				//		body(Files.readAllBytes(Paths.get(PATH))).
						when().
				contentType(ContentType.JSON).
				post("http://192.168.56.139:8080/sdw/rest/authentication/logout");

		response = given().
				body(Files.readAllBytes(Paths.get(PATH_LOGIN_AGENT))).
				when().
				contentType(ContentType.JSON).
				post("http://192.168.56.139:8080/sdw/rest/authentication/login");
	}
}
