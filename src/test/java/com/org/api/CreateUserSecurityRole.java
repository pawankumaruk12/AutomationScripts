package com.org.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Repository;
import com.org.api.model.UserSecurityRole;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUserSecurityRole extends CommonLogin {
    @Test
    public void testCreateUserSecurityRole() throws Exception {
        String userId = (String) Repository.getValue("userId");
        String securityitemId = (String) Repository.getValue("securityItemId");
        String securityRoleTypeId = (String) Repository.getValue("securityRoleTypeId");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        UserSecurityRole userSecurityRole = new UserSecurityRole();
        userSecurityRole.setSecurityItemId(securityitemId);
        userSecurityRole.setSecurityRoleTypeId(securityRoleTypeId);
        userSecurityRole.setUserId(userId);
        Gson gson = new Gson();
        String json = gson.toJson(userSecurityRole);

        response = given().
                body(json).
                when()
                .cookie(JSESSIONID,jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken)
                .contentType(ContentType.JSON).
                post(API_PATH + "usersecurityrole/create").then()
                .assertThat().statusCode(201).and().extract().response();
        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();
    }
}
