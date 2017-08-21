package com.org.api;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import com.org.api.unittest.DepartmentService;
public class DeleteDepartment extends CommonLogin {
	public static String DEPARTMENT_ID;

	@Test
	public void DeleteDepartments() throws Exception {
		String jsessionId= resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
		
		 DEPARTMENT_ID =DepartmentService.getLastDepartmentId(null, jsessionId, xsrfToken);			
		
		resp = given().
				when()
				.cookie("JSESSION",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "department/delete/" + DEPARTMENT_ID);
		System.out.println(resp.getBody().asString());
		Assert.assertEquals(resp.getStatusCode(), 200);
		if (resp.getStatusCode() == 200){
			System.out.println("API is working fine");
			System.out.println(resp.getStatusCode());
		}
		else
			System.out.println("API is not working fine");
				
	}
}
