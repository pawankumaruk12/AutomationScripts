package com.org.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Project;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApprovePrivacyNotice extends CommonLogin {

        @Test
        public void testApprovePrivacyNotice() throws Exception{
            String projectId = (String) Repository.getValue("projectId");

            Project project = new Project();
            project.setId(projectId);

            String jsonTemp= gson.toJson(project);

            String jsessionId = response.cookie(JSESSIONID);
            String xsrfToken = response.cookie(XSRF_TOKEN);

            response = given().body(jsonTemp).when().cookie(JSESSIONID, jsessionId)
                    .cookie(XSRF_TOKEN, xsrfToken).contentType(ContentType.JSON).
                            post(API_PATH+"project/" + projectId +"/privacyNotice/approve").then().assertThat().statusCode(200).and().extract().response();
            JsonParser parser= new JsonParser();
            JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();


        }
    }

