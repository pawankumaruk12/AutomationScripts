package com.org.api;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class AccountsType extends CommonLogin {
    @Test
    public void Accounts_Types() throws Exception {

        String jsessionId = response.cookie("JSESSIONID");
        String xsfrToken = response.cookie("XSRF-TOKEN");

        response = given().
                body(Files.readAllBytes(Paths.get("src/test/resources/AccountType.json"))).
                when()
                .cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsfrToken)
                .contentType(ContentType.JSON).
                        post(API_PATH + "account/types");

    }

}
