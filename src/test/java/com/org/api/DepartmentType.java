package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;

import com.google.gson.Gson;
import com.org.api.model.Department;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.org.api.unittest.ProjectService;

public class DepartmentType extends CommonLogin {

	@Test
	public void DepartmentTypes() throws Exception {
		//String departmentId = (String) Repository.getValue("departmentId");
		String projectId = (String) Repository.getValue("projectId");

		String jsessionId= response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");

		Department department = new Department();
		department.setProjectId(projectId);

		Gson gson = new Gson();
		String json = gson.toJson(department);


		response = given().
				
				body(json).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "department/types/" + projectId);
		
		//System.out.println(response.getBody().asString());
		AssertJUnit.assertEquals(response.getStatusCode(), 200);

				
	}

}
