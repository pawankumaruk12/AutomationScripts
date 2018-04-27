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

public class EnableorDisableProjectEnvelopeTemplate extends CommonLogin {
    @Test
    public void testEnableorDisableProjectEnvelopeTemplate() throws Exception {
        String envelopeTemplateId = (String) Repository.getValue("envelopeTemplateId");
        String envelopeTemplateName = (String) Repository.getValue("envelopeTemplateName");
        String envelopeTemplateVersionId = (String) Repository.getValue("envelopeTemplateVersionId");
        String projectEnvelopeTemplateId = (String) Repository.getValue("projectEnvelopeTemplateId");
        String projectId = (String) Repository.getValue("projectId");
        String templateId = (String) Repository.getValue("templateId");
        String projectEnvelopeTemplateVersionId = (String) Repository.getValue("projectEnvelopeTemplateVersionId");
        //EnvelopeTemplate
        EnvelopeTemplate envelopeTemplate = new EnvelopeTemplate();
        envelopeTemplate.setId(envelopeTemplateId);
        envelopeTemplate.setName(envelopeTemplateName);
        envelopeTemplate.setVersionId(envelopeTemplateVersionId);
        //ProjectEnvelopeTemplate
        ProjectEnvelopeTemplate projectEnvelopeTemplate = new
                ProjectEnvelopeTemplate();
        projectEnvelopeTemplate.setId(projectEnvelopeTemplateId);
        projectEnvelopeTemplate.setActive(true);
        projectEnvelopeTemplate.setProjectId(projectId);
        projectEnvelopeTemplate.setTemplateId(templateId);
        projectEnvelopeTemplate.setVersionId(projectEnvelopeTemplateVersionId);
        projectEnvelopeTemplate.setParentId(null);
        projectEnvelopeTemplate.setShareAll(true);
        //ProjectEnvelopeTemplateWithEnvelopeTemplate
        ProjectEnvelopeTemplateWithEnvelopeTemplate projectEnvelopeTemplateWithEnvelopeTemplate = new
                ProjectEnvelopeTemplateWithEnvelopeTemplate();
        projectEnvelopeTemplateWithEnvelopeTemplate.setEnvelopeTemplate(envelopeTemplate);
        projectEnvelopeTemplateWithEnvelopeTemplate.setProjectEnvelopeTemplate(projectEnvelopeTemplate);

        String jsonTemp = gson.toJson(projectEnvelopeTemplateWithEnvelopeTemplate);

        String jsessionid = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        response = given().
                body(jsonTemp).when()
                .cookie(JSESSIONID, jsessionid)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "projectenvelopetemplate/enableordisable").then()
                .assertThat().statusCode(200).and().extract().response();
        JsonParser parser= new JsonParser();
        JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();

        String versionId = fullBody.get(RESULTS).getAsJsonArray().get(0).getAsJsonObject().getAsJsonObject("projectEnvelopeTemplate").get("versionId").getAsString();
        Repository.addData("projectEnvelopeTemplateVersionId", versionId);
    }
}
