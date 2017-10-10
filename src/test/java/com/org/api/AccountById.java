package com.org.api;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Account;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AccountById extends CommonLogin {
    @Test
    public void TestAccountById() {
        String accountId = (String) Repository.getValue("accountId");

        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        Account account = new Account();
        Gson gson = new Gson();
        String json = gson.toJson(account);

        Response createResponse = given().
                body(json).
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "account/" + accountId).then()
                .assertThat().statusCode(200).and().extract().response();

        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(createResponse.getBody().asString()).getAsJsonObject();

        String accountPersonDBId = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("links").get("accountPersonDBId").getAsString();
        Repository.addData("accountPersonDBId", accountPersonDBId);
    }
}
