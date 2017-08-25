package com.org.api;
//Tested and working on 31st March 2017
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
//import java.nio.file.Files;
//import java.nio.file.Paths;




import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ListProjectMemberByPagination extends CommonLogin{
	@Test
	public void ListProjectMemberByPaginations() throws IOException {

		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");

		Response res= given().
				body(Files.readAllBytes(Paths.get("src/test/resources/ProjectMemberList.json"))).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
						contentType(ContentType.JSON).
						post(API_PATH + "projectmember/list").
						then().assertThat().statusCode(200).and().extract().response();
		//body("results[0].projectMember.id",equalTo("UG12Ew3Ww2vRVfrYSDEvqg"));
		String responseString = res.asString();

		JsonPath js = new JsonPath(responseString);
		String ProjectMemberIds = js.get("results[0].projectMember.id");
		System.out.println(ProjectMemberIds);


		System.out.println(res.getBody().asString());
		//String X = resp.getBody().jsonPath();
		Assert.assertEquals(resp.statusCode(), 200);
		Assert.assertEquals(HttpStatus.SC_OK,200);

		if (res.getStatusCode()==200){
			System.out.println("API is working fine");
			System.out.println(resp.getStatusCode());
		}
		else
		{
			System.out.println("API is not working fine");
		}

	}
}
