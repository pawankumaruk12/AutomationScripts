package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class RemoveAgentFromProjectMember extends CommonLogin{

	@Test
	public void RemoveAgentFromProjectMembers() throws Exception {
	
	String jsessionId = resp.cookie("JSESSIONID");
	String xsrfToken = resp.cookie("XSRF-TOKEN");
	//RestAssured.baseURI = "http://192.168.56.139:8080/sdw/rest/";
	resp = given().
			body(Files.readAllBytes(Paths.get("src/test/resources/ListbyProjectDoc.json"))).
			when()
			.cookie("JSESSIONID",jsessionId)
			.cookie("XSRF-TOKEN",xsrfToken).
			contentType(ContentType.JSON).
			post(API_PATH + "projectmember/removeagent/:id");
	System.out.println(resp.getBody().asString());
	AssertJUnit.assertEquals(resp.statusCode(), 200);
	
	if (resp.getStatusCode()==200){
		System.out.println("API is working fine");
		System.out.println(resp.getStatusCode());
	}
	else
	{
		System.out.println("API is not working fine");
	}

}


}
