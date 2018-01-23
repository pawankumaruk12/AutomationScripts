package com.org.api;

import com.org.api.model.EnvelopeTemplate;
import com.org.api.model.ProjectEnvelopeTemplate;
import com.org.api.model.ProjectEnvelopeTemplateWithEnvelopeTemplate;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateProjectEnvelopeTemplate extends CommonLogin {
    @Test
    public void testUpdateProjectEnvelopeTemplate() throws Exception {
        String envelopeTemplateId = (String) Repository.getValue("envelopeTemplateId");
        String envelopeTemplateName = (String) Repository.getValue("envelopeTemplateName");
        String envelopeTemplateVersionId = (String) Repository.getValue("envelopeTemplateVersionId");
        String tempjson = (String) Repository.getValue("json");
        String type = (String) Repository.getValue("type");
        String projectEnvelopeTemplateId = (String) Repository.getValue("projectEnvelopeTemplateId");
        boolean active = (boolean) Repository.getValue("active");
        String projectId = (String) Repository.getValue("projectId");
        String templateId = (String) Repository.getValue("templateId");
        String projectEnvelopeTemplateVersionId = (String) Repository.getValue("projectEnvelopeTemplateVersionId");
        //EnvelopeTemplate
        EnvelopeTemplate envelopeTemplate = new EnvelopeTemplate();
        envelopeTemplate.setId(envelopeTemplateId);
        envelopeTemplate.setName(envelopeTemplateName);
        envelopeTemplate.setVersionId(envelopeTemplateVersionId);
        envelopeTemplate.setJson(tempjson);
        envelopeTemplate.setType(type);
        //ProjectEnvelopeTemplate
        ProjectEnvelopeTemplate projectEnvelopeTemplate = new
                ProjectEnvelopeTemplate();
        projectEnvelopeTemplate.setId(projectEnvelopeTemplateId);
        projectEnvelopeTemplate.setActive(active);
        projectEnvelopeTemplate.setProjectId(projectId);
        projectEnvelopeTemplate.setTemplateId(templateId);
        projectEnvelopeTemplate.setVersionId(projectEnvelopeTemplateVersionId);
        projectEnvelopeTemplate.setParentId(null);
        // projectEnvelopeTemplate.setSelfService(false);
        projectEnvelopeTemplate.setShareAll(true);
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
                        post(API_PATH + "projectenvelopetemplate/update").then()
                .assertThat().statusCode(200).and().extract().response();

    }
}
