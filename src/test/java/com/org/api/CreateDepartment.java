package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
// working fine on 31st Aug, but change the dept name on json everytime
@Ignore
public class CreateDepartment extends CommonLogin{
	@Test
	public void CreateDepartments() throws Exception {
		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
		String CreateDeptJson = "src/test/resources/CreateDept.json";
		
		resp = given().
				body(Files.readAllBytes(Paths.get(CreateDeptJson))).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "department/create");
		System.out.println(resp.getBody().asString());
		AssertJUnit.assertEquals(resp.getStatusCode(), 200);
		if (resp.getStatusCode() == 200) {
			System.out.println("API is working fine");
			System.out.println(resp.getStatusCode());
		}
		else {
			System.out.println("API is not working");
		}	
				
	}

}
