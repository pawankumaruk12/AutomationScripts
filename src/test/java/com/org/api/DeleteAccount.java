package com.org.api;

import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteAccount extends CommonLogin {

    @Test

    public void testDeleteAccount() throws Exception {

        String accountId = (String) Repository.getValue("accountId");

        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");


        response = given().
                when()
                .cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "account/delete/" + accountId).
                        then().
                        assertThat().statusCode(200).and().extract().response();

        AssertJUnit.assertEquals(response.getStatusCode(), 200);


    }

}
