package com.org.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProjectMemberById extends CommonLogin {
    @Test
    public void testProjectMemberById() throws Exception {
        String projectMemberId = (String) Repository.getValue("projectMemberId");

        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);

        response = given().
                when()
                .cookie(JSESSIONID,jsessionId)
                .cookie(XSRF_TOKEN,xsrfToken)
                .contentType(ContentType.JSON).
                post(API_PATH + "projectmember/" + projectMemberId).then()
                .assertThat().statusCode(200).and().extract().response();
        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();


        String userId = fullBody.get(RESULTS).getAsJsonArray().get(0).getAsJsonObject().get("userId").getAsString();
        Repository.addData("userId", userId);

    }
}
