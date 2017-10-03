package com.org.api;

import com.google.gson.Gson;
import com.org.api.model.EnvelopeTemplate;
import com.org.api.model.ProjectEnvelopeTemplate;
import com.org.api.model.ProjectEnvelopeTemplateWithEnvelopeTemplate;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateProjectEnvelopeTemplate extends CommonLogin {
    @Test
    public void testCreateprojectEnvelopeTemplate() throws Exception {
        String projectId = (String) Repository.getValue("projectId");
        String json = (String) Repository.getValue("json");
        //ProjectEnvelopeTemplate
        ProjectEnvelopeTemplate projectEnvelopeTemplate = new
                ProjectEnvelopeTemplate();
        projectEnvelopeTemplate.setProjectId(projectId);
        projectEnvelopeTemplate.setShareAll(true);
        //EnvelopeTemplate
        EnvelopeTemplate envelopeTemplate = new EnvelopeTemplate();
        envelopeTemplate.setName("Auto_API_Test_Template");
        envelopeTemplate.setJson(json);
        envelopeTemplate.setApprovalRequired(false);
        envelopeTemplate.setType("modular");
        //ProjectEnvelopeTemplateWithEnvelopeTemplate
        ProjectEnvelopeTemplateWithEnvelopeTemplate projectEnvelopeTemplateWithEnvelopeTemplate = new
                ProjectEnvelopeTemplateWithEnvelopeTemplate();
        projectEnvelopeTemplateWithEnvelopeTemplate.setEnvelopeTemplate(envelopeTemplate);
        projectEnvelopeTemplateWithEnvelopeTemplate.setProjectEnvelopeTemplate(projectEnvelopeTemplate);

        Gson gson = new Gson();
        String jsonTemp = gson.toJson(projectEnvelopeTemplateWithEnvelopeTemplate);

        String jsessionid = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        response = given().
                body(jsonTemp).when()
                .cookie(JSESSIONID, jsessionid)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "projectenvelopetemplate/create").then()
                .assertThat().statusCode(201).and().extract().response();
    }
}
