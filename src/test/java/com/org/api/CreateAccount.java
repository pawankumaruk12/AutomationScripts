package com.org.api;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Account;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import static io.restassured.RestAssured.given;


public class CreateAccount extends CommonLogin {


    @Test
    public void testAccountCreation() {



        Account account = new Account();
        account.setName("AutoAccount" + new Date());
        account.setDescription("Automation Account");
        account.setTypeId(2);
        account.setAccountId(1);

        String json = gson.toJson(account);

        Response createResponse = given().
                body(json).
                when()
                .cookie(JSESSIONID, getJsessionId())
                .cookie(XSRF_TOKEN, getXSRFToken()).
                        contentType(ContentType.JSON).
                        post(API_PATH + "account/create").then()
                .assertThat().statusCode(201).and().extract().response();

        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(createResponse.getBody().asString()).getAsJsonObject();

        String accountId = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("account").get("id").getAsString();
        Repository.addData("accountId", accountId);
        String name = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("account").get("name").getAsString();
        Repository.addData("name", name);
        String description = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("account").get("description").getAsString();
        Repository.addData("description", description);
        String stringTypeId = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("account").get("typeId").getAsString();
        Repository.addData("stringTypeId", stringTypeId);

    }


    @Test(dependsOnMethods = {"testAccountCreation"})
    public void CheckingDuplicateAccount() throws Exception {
        String createAcc_json_path = "src/test/resources/createAccount.json";
        Response res = given().
                body(Files.readAllBytes(Paths.get(createAcc_json_path))).
                when()
                .cookie(JSESSIONID, getJsessionId())
                .cookie(XSRF_TOKEN, getXSRFToken()).
                        contentType(ContentType.JSON).
                        post(API_PATH + "account/create").then()
                .assertThat().statusCode(400).and().extract().response();

    }


     @Test(dependsOnMethods = {"testAccountCreation"})
    public void CheckingInvalidSession403() throws Exception {

        String createAcc_json_path = "src/test/resources/createAccount.json";
        Response res = given().
                body(Files.readAllBytes(Paths.get(createAcc_json_path))).
                        when()
                        .contentType(ContentType.JSON).
                        post(API_PATH + "account/create").then()
                .assertThat().statusCode(403).and().extract().response();
    }


	@Test(dependsOnMethods = {"testAccountCreation"})
    public void createAccountWithoutPayload400() throws Exception {
        Response res = given().
                when()
                .cookie(JSESSIONID, getJsessionId())
                .cookie(XSRF_TOKEN, getXSRFToken()).
                        contentType(ContentType.JSON).
                        post(API_PATH + "account/create").then()
                .assertThat().statusCode(400).and().extract().response();
    }

    @Test(dependsOnMethods = {"testAccountCreation"})
    public void createAccountMediaType415() throws Exception {
        String createAcc_json_path = "src/test/resources/createAccount.json";
        Response res = given().
                 body(Files.readAllBytes(Paths.get(createAcc_json_path))).
                        when()
                .cookie(JSESSIONID, getJsessionId())
                .cookie(XSRF_TOKEN, getXSRFToken()).
                        post(API_PATH + "account/create").then()
                .assertThat().statusCode(415).and().extract().response();
    }

}
