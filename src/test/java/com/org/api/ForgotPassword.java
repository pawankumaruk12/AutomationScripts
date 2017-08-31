package com.org.api;
//Tested and working on 29th Aug 2017
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class ForgotPassword extends CommonLogin {
	@Test
	public void ForgotPasswords() throws Exception {
		resp = given()
				.body(Files.readAllBytes(Paths
						.get("src/test/resources/ForgotPassword.json"))).
						when()
							.contentType(ContentType.JSON)
								.post(API_PATH + "authentication/forgotpassword");
		System.out.println(resp.getBody().asString());
		AssertJUnit.assertEquals( resp.getStatusCode(), 200);
		if (resp.getStatusCode()==200){
			System.out.println("API is working fine");
			System.out.println(resp.getStatusCode());
		}
		else {
			System.out.println("API is not working fine");

		}
	}

}
