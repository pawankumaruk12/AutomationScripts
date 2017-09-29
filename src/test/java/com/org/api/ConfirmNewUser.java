package com.org.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Account;
import com.org.api.model.Invitation;
import com.org.api.model.NewUser;
import com.org.api.model.Repository;
import com.sun.org.apache.regexp.internal.RE;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;

public class ConfirmNewUser extends CommonLogin {

    @Test
    public void testConfirmNewUser() {

        String invitationIdStr = (String) Repository.getValue("invitationIdStr");
        String username = (String) Repository.getValue("username");
        String password = (String) Repository.getValue("password");
        // Boolean contactByMail = (Boolean) Repository.getValue("contactByMail");
        // Boolean contactByTelephone = (Boolean) Repository.getValue("contactByTelephone");
        // Boolean contactByEmail = (Boolean) Repository.getValue("contactByEmail");
        String firstName = (String) Repository.getValue("firstName");
        String lastName = (String) Repository.getValue("lastName");

        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");

        NewUser newUser = new NewUser();
        newUser.setContactByEmail(true);
        newUser.setContactByMail(true);
        newUser.setContactByTelephone(true);
        newUser.setInvitationIdStr(invitationIdStr);
        newUser.setPassword("0nBoard!ng12");
        Long i = (Long) new Date().getTime();
        newUser.setUsername(firstName + lastName + i + "@sd.com");
        newUser.setSecurityCode("1314");


        Gson gson = new Gson();
        String json = gson.toJson(newUser);
        Repository.addData("username", username);
        Repository.addData("password", password);

        Response createResponse = given().
                body(json).
                when()
                .cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "invitation/public/confirm/new").then()
                .assertThat().statusCode(200).and().extract().response();


        //JsonParser parser = new JsonParser();
        // JsonObject fullBody = parser.parse(createResponse.getBody().asString()).getAsJsonObject();

        //  String accountId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("account").get("id").getAsString();
        //  Repository.addData("accountId", accountId);
        //  String username = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size()-1).getAsJsonObject().getAsJsonObject("newUser").get("username").getAsString();
        //  Repository.addData("username", username);

        //   String password = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size()-1).getAsJsonObject().getAsJsonObject("newUser").get("password").getAsString();
        // Repository.addData("password",password);
    }


    @AfterTest
   // @Test   testConfirmNewUser() dependsOnMethods
    public void loginAsNewTeamMember() throws Exception {

        loginAsTeamMember();
    }
}
