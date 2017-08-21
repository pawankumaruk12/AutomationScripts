package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
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
	}

}
