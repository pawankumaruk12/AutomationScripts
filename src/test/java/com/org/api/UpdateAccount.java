package com.org.api;

import com.google.gson.Gson;
import com.org.api.model.Account;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

//import java.nio.file.Files;
//import java.nio.file.Paths;

public class UpdateAccount extends CommonLogin {


    @Test
    public void testUpdateAccount() {

        String accountId = (String) Repository.getValue("accountId");
        String name = (String) Repository.getValue("name");
        String typeId = (String) Repository.getValue("stringTypeId");
        String description = (String) Repository.getValue("description");

        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");


        Account account = new Account();
        account.setId(accountId);
        account.setName(name);
        account.setAccountId(1);
        account.setTypeId(1);
        account.setDescription(description);
        account.setVersionId(1);


        Gson gson = new Gson();
        String json = gson.toJson(account);

        Response updateResponse = given().
                body(json).
                when()
                .cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "account/update").
                        then().
                        assertThat().statusCode(200).and().extract().response();
    }
}
