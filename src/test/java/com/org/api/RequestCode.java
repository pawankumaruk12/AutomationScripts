package com.org.api;

import com.org.api.model.Invitation;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequestCode extends CommonLogin {

    @Test
    public void testRequestCode() throws Exception {

        final String securityCodeValue ="1314";
        String invitationIdStr = (String) Repository.getValue("invitationIdStr");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        Invitation invitation = new Invitation();
        invitation.setInvitationIdStr(invitationIdStr);
        invitation.setSecurityCode(securityCodeValue);

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
