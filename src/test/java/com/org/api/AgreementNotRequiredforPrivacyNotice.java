package com.org.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Project;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import static com.org.api.CommonLogin.*;
import static io.restassured.RestAssured.given;


public class AgreementNotRequiredforPrivacyNotice extends CommonLogin{
    @Test
    public void testAgreemnetNotRequiredforPN() throws Exception{
        String projectId = (String) Repository.getValue("projectId");

        Project project = new Project();
        project.setId(projectId);

        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
     /*   ResponseBody body=given()
                .when().cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH+"project/"+projectId+"/privacyNotice/agreementnotrequired").getBody();
        System.out.println(body);*/

      response = given()
                .when().cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                       contentType(ContentType.JSON).
                        post(API_PATH+"project/" + projectId +"/privacyNotice/agreementnotrequired").then().assertThat().statusCode(200).and().extract().response();

       JsonParser parser= new JsonParser();
      JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();

    }
}
