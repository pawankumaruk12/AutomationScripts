package com.org.api;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import com.org.api.unittest.DepartmentService;
public class DeleteDepartment extends CommonLogin {
	public static String DEPARTMENT_ID;

	@Test(enabled = false)
	public void DeleteDepartments() throws Exception {
		String jsessionId= response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");
		
		 DEPARTMENT_ID =DepartmentService.getLastDepartmentId(null, jsessionId, xsrfToken);			
		
		response = given().
				when()
				.cookie("JSESSION",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "department/delete/" + DEPARTMENT_ID);
		System.out.println(response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 200);
		if (response.getStatusCode() == 200){
			System.out.println("API is working fine");
			System.out.println(response.getStatusCode());
		}
		else
			System.out.println("API is not working fine");
				
	}
}
