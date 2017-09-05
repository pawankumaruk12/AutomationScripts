package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
@Ignore
public class RemoveAgentFromProjectMember extends CommonLogin{

	@Test(enabled = false)
	public void RemoveAgentFromProjectMembers() throws Exception {
	
	String jsessionId = response.cookie("JSESSIONID");
	String xsrfToken = response.cookie("XSRF-TOKEN");
	//RestAssured.baseURI = "http://192.168.56.139:8080/sdw/rest/";
	response = given().
			body(Files.readAllBytes(Paths.get("src/test/resources/ListbyProjectDoc.json"))).
			when()
			.cookie("JSESSIONID",jsessionId)
			.cookie("XSRF-TOKEN",xsrfToken).
			contentType(ContentType.JSON).
			post(API_PATH + "projectmember/removeagent/:id");
	System.out.println(response.getBody().asString());
	AssertJUnit.assertEquals(response.statusCode(), 200);
	
	if (response.getStatusCode()==200){
		System.out.println("API is working fine");
		System.out.println(response.getStatusCode());
	}
	else
	{
		System.out.println("API is not working fine");
	}

}


}
