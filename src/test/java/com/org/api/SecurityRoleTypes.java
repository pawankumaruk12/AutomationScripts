package com.org.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;

import static io.restassured.RestAssured.given;

public class SecurityRoleTypes extends CommonLogin {
    @Test
    public void testSecurityRoleTypes() throws Exception {

        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        response = given().
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "envelope/securityroletypes").then()
                .assertThat().statusCode(200).and().extract().response();
        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();
        Iterator<JsonElement> iterator = fullBody.get(RESULTS).getAsJsonArray().iterator();
        String securityRoleIdForHOD = null;
        while (iterator.hasNext()) {
            JsonObject securityRole = iterator.next().getAsJsonObject();
            if (securityRole.has("name") && "HOD".equals(securityRole.get("name").getAsString())) {
                securityRoleIdForHOD = securityRole.get("id").getAsString();
                Repository.addData("securityRoleId", securityRoleIdForHOD);
                break;
            }
        }
        Assert.assertNotNull(securityRoleIdForHOD, "There is no HOD role");
    }
}