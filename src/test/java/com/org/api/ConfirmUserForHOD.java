package com.org.api;

import com.org.api.model.NewUser;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;

public class ConfirmUserForHOD extends CommonLogin {
    @Test
    public void testConirmUserForHOD() {
        String invitationIdStr = (String) Repository.getValue("invitationIdStr");
        String firstName = (String) Repository.getValue("firstName");
        String lastName = (String) Repository.getValue("lastName");
        NewUser newUser = new NewUser();
        newUser.setContactByEmail(true);
        newUser.setContactByMail(true);
        newUser.setContactByTelephone(true);
        newUser.setInvitationIdStr(invitationIdStr);
        newUser.setPassword(PASSWORD);
        Long timeInNumber = (Long) new Date().getTime();
        String username = (firstName + lastName + timeInNumber + "@sd.com");
        newUser.setUsername(username);
        newUser.setSecurityCode(SECURITY_CODE);
        Repository.addData("userNameForHOD",username);
        Repository.addData("password",PASSWORD);

        String json = gson.toJson(newUser);

        Response createResponse = given().
                body(json).
                when()
                .cookie(JSESSIONID, getJsessionId())
                .cookie(XSRF_TOKEN, getXSRFToken()).
                        contentType(ContentType.JSON).
                        post(API_PATH + "invitation/public/confirm/new").then()
                .assertThat().statusCode(200).and().extract().response();
        System.out.println(response);

    }

    @Test (dependsOnMethods = {"testConirmUserForHOD"})
    public void loginAsNewHOD() throws Exception {
        loginAsHOD();
    }
}
