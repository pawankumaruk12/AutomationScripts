package com.org.api;
import com.org.api.model.Repository;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import com.org.api.unittest.ProjectService;
public class DepartmentById extends CommonLogin {

	@Test
	public void testDepartmentById() throws Exception {
		String departmentId = (String) Repository.getValue("departmentId");

		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");

		
		response = given().

				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "department/" + departmentId);
		
		//System.out.println(response.getBody().asString());
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		

				
	}

}