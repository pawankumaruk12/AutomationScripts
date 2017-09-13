package com.org.api;
import com.org.api.model.Repository;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import com.org.api.unittest.DepartmentService;
public class DeleteDepartment extends CommonLogin {


	@Test
	public void testDeleteDepartments() throws Exception {
		String departmentId = (String) Repository.getValue("departmentId");
		String jsessionId= response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");

		
		response = given().
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "department/delete/" + departmentId).
						then().
						assertThat().statusCode(200).and().extract().response();
		AssertJUnit.assertEquals( response.getStatusCode(), 200);
				
	}
}
