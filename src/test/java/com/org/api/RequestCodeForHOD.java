package com.org.api;

import com.google.gson.Gson;
import com.org.api.model.Invitation;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequestCodeForHOD extends CommonLogin{
    @Test
    public void testRequestCode() throws Exception {
        String invitationIdStr = (String) Repository.getValue("invitationIdStr");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        Invitation invitation = new Invitation();
        invitation.setInvitationIdStr(invitationIdStr);
        invitation.setSecurityCode(SECRURITYCODEVALUE);
        Gson gson = new Gson();
        String json = gson.toJson(invitation);
        response = given().
                body(json).
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "invitation/public/requestcode").then()
                .assertThat().statusCode(200).and().extract().response();
    }
}
