package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.ProjectMember;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class CreateProjectMember extends CommonLogin{
	@Test
	public void testCreatesProjectMembers() throws Exception{

		String personId = (String) Repository.getValue("personId");
		String departmentId = (String) Repository.getValue("departmentId");
		String projectEmail = (String) Repository.getValue("personalEmail");
		String versionId = (String) Repository.getValue("versionId");
		String teleCode = (String) Repository.getValue("teleCode");
		String countryABBRCode = (String) Repository.getValue("countryABBRCode");
		String roleTypeId = (String) Repository.getValue("roleTypeId");



		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");

		ProjectMember projectmember = new ProjectMember();
		projectmember.setPersonId(personId);
		projectmember.setDepartmentId(departmentId);

		//projectmember.setRoleTypeId(1);

		projectmember.setUseit(true);
		projectmember.setVersionId(versionId);
		projectmember.setTeleCode(teleCode);
		projectmember.setCountryABBRCode(countryABBRCode);
		projectmember.setAgencyId(null);
		projectmember.setAgentPersonId(null);
		projectmember.setRoleTypeId(roleTypeId);


		Gson gson = new Gson();
		String json = gson.toJson(projectmember);

		
		response = given().
				body(json).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "projectmember/create")
				.then()
	.assertThat().statusCode(201).and().extract().response();

		JsonParser parser = new JsonParser();
		JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();




	//	String roleTypeId  = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size()-1).getAsJsonObject().getAsJsonObject("projectmember").get("roleTypeId").getAsString();
	//	Repository.addData("stringroleTypeId",stringroleTypeId);

		projectEmail  = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size()-1).getAsJsonObject().getAsJsonObject("projectmember").get("projectEmail").getAsString();
		Repository.addData("projectEmail",projectEmail);

		
	}
	
}
