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

}
