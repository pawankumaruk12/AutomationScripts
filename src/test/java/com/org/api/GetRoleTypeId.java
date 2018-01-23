package com.org.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.ProjectMember;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetRoleTypeId extends CommonLogin {

    @Test
    public void testRoleTypeId() {
        String departmentId = (String) Repository.getValue("departmentId");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        ProjectMember projectmember = new ProjectMember();

        String json = gson.toJson(projectmember);
        response = given().
                body(json).
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "projectmember/roletypes/" + departmentId).then()
                .assertThat().statusCode(200).and().extract().response();
        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();

        String roleTypeId = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().get("id").getAsString();
        Repository.addData("roleTypeId", roleTypeId);
    }
}