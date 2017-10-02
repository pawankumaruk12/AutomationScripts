package com.org.api;

import com.google.gson.Gson;
import com.org.api.model.Invitation;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CheckSecurity extends CommonLogin {
    @Test
    public void testCheckSecurity() throws Exception {
        final String securityCodeValue ="1314";
        String securityCode = (String) Repository.getValue("securityCode");
        String invitationIdStr = (String) Repository.getValue("invitationIdStr");

        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");

        Invitation invitation = new Invitation();
        invitation.setInvitationIdStr(invitationIdStr);
        invitation.setSecurityCode(securityCodeValue);

        Gson gson = new Gson();
        String json = gson.toJson(invitation);

        response = given().
                body(json).
                when()
                .cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "invitation/public/checksecurity").then()
                .assertThat().statusCode(200).and().extract().response();
    }
}
