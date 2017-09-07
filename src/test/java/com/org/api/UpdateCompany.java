package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;

import com.org.api.model.Repository;
import io.restassured.http.ContentType;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
//Issue needs to be fixed
@Ignore
public class UpdateCompany extends CommonLogin {
	@Test(enabled = false)
	public void testUpdateCompany() throws Exception{

		String accountId = (String) Repository.getValue("accountId");
		String name = (String) Repository.getValue("name");
		String typeId =  (String) Repository.getValue("stringTypeId");
		String description = (String) Repository.getValue("description");

		String jsessionId = response.cookie("JSESSIONID");
		String xsrfToken = response.cookie("XSRF-TOKEN");


		//String updatecompanyjson = "src/resources/UpdateCompany.json";
		
		response = given().
				body(Files.readAllBytes(Paths.get("src/test/resources/UpdateCompany.json"))).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN",xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "company/update");
		//System.out.println(response.getBody().asString());
		AssertJUnit.assertEquals( response.getStatusCode(), 200);

				
	}

}
