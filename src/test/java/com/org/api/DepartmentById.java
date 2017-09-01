package com.org.api;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import com.org.api.unittest.ProjectService;
public class DepartmentById extends CommonLogin {
	public static String PROJECT_ID;
	@Test(enabled = false)
	public void DepartmentTypes() throws Exception {
		String jsessionId = resp.cookie("JSESSIONID");
		String xsrfToken = resp.cookie("XSRF-TOKEN");
		PROJECT_ID = ProjectService.getLastProjectId(null, jsessionId, xsrfToken);
		System.out.println(PROJECT_ID);
		
		resp = given().
				
				//body(Files.readAllBytes(Paths.get("src/test/resources/DepartmentType.json"))).
				when()
				.cookie("JSESSIONID",jsessionId)
				.cookie("XSRF-TOKEN", xsrfToken).
				contentType(ContentType.JSON).
				post(API_PATH + "department/types" + PROJECT_ID);
		
		System.out.println(resp.getBody().asString());
		AssertJUnit.assertEquals(resp.getStatusCode(), 200);
		
		if (resp.getStatusCode()==200) {
			System.out.println("API is working fine");
			System.out.println(resp.getStatusCode());
		}
		else
		{System.out.println("API is not working fine");
			
		}			
				
	}

}