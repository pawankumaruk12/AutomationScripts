package com.org.api;

import com.google.gson.Gson;
import com.org.api.model.NewUser;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;

public class ConfirmUserForHOD extends CommonLogin {
    @Test
    public void testConfirmNewUser() {
        String invitationIdStr = (String) Repository.getValue("invitationIdStr");
        String username = (String) Repository.getValue("username");
        String firstName = (String) Repository.getValue("firstName");
        String lastName = (String) Repository.getValue("lastName");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        NewUser newUser = new NewUser();
        newUser.setContactByEmail(true);
        newUser.setContactByMail(true);
        newUser.setContactByTelephone(true);
        newUser.setInvitationIdStr(invitationIdStr);
        newUser.setPassword(PASSWORD);
        Long timeInNumber = (Long) new Date().getTime();
        username = (firstName + lastName + timeInNumber + "@sd.com");
        newUser.setUsername(username);
        newUser.setSecurityCode(SECURITY_CODE);
        Repository.addData("username",username);
        Repository.addData("password",PASSWORD);
        Gson gson = new Gson();
        String json = gson.toJson(newUser);

        Response createResponse = given().
                body(json).
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "invitation/public/confirm/new").then()
                .assertThat().statusCode(200).and().extract().response();
    }

    @AfterTest
    public void loginAsNewTeamMember() throws Exception {
        loginAsTeamMember();
    }
}
