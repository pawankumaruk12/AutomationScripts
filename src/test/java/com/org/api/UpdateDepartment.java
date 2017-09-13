package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;

import com.google.gson.Gson;
import com.org.api.model.Department;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class UpdateDepartment extends CommonLogin{
	@Test
	public void testUpdateDepartment() throws Exception{

		String id = (String) Repository.getValue("departmentId");
		String name = (String) Repository.getValue("name");
		String description = (String) Repository.getValue("description");
		String projectId = (String) Repository.getValue("projectId");
		String departmentTypeId = (String) Repository.getValue("departmentTypeId");
		String versionId = (String) Repository.getValue("versionId");

		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");

		Department department = new Department();
		department.setProjectId(projectId);
		department.setId(id);
		department.setName(name);
		department.setDescription(description);
	department.setDepartmentTypeId(departmentTypeId);
	department.setVersionId(versionId);

		Gson gson = new Gson();
		String json = gson.toJson(department);


		response = given().
				body(json).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "department/update"+"?id="+id).
						//post(API_PATH + "department/update").
						then().
						assertThat().statusCode(200).and().extract().response();

				
	}

}
