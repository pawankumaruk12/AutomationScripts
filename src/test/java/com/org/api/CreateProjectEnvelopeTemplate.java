package com.org.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
       ;
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

        JsonParser parser= new JsonParser();
        JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();
        //ProjectEnvelopeTemplate
        String projectEnvelopeTemplateId = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("projectEnvelopeTemplate").get("id").getAsString();
        Repository.addData("projectEnvelopeTemplateId", projectEnvelopeTemplateId);
        String templateId = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("projectEnvelopeTemplate").get("templateId").getAsString();
        Repository.addData("templateId", templateId);
        String projectEnvelopeTemplateVersionId = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("projectEnvelopeTemplate").get("versionId").getAsString();
        Repository.addData("projectEnvelopeTemplateVersionId", projectEnvelopeTemplateVersionId);
        //EnvelopeTemplate
        String envelopeTemplateId = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("envelopeTemplate").get("id").getAsString();
        Repository.addData("envelopeTemplateId", envelopeTemplateId);
        String envelopeTemplateName = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("envelopeTemplate").get("name").getAsString();
        Repository.addData("envelopeTemplateName", envelopeTemplateName);
        String envelopeTemplateVersionId = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("envelopeTemplate").get("versionId").getAsString();
        Repository.addData("envelopeTemplateVersionId", envelopeTemplateVersionId);
    }
}
