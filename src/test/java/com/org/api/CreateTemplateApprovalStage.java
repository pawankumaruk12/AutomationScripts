package com.org.api;

import com.google.gson.Gson;
import com.org.api.model.ProjectEnvelopeTemplateWithTemplateApprovalStage;
import com.org.api.model.Repository;
import com.org.api.model.TemplateApprovalStage;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateTemplateApprovalStage extends CommonLogin {
    @Test
    public void testCreateTemplateApprovalStage() throws Exception {
        String envelopeTemplateId = (String) Repository.getValue("envelopeTemplateId");
        String securityRoleId = (String) Repository.getValue("securityRoleId");
        String projectOrAccountEnvelopetemplateId = (String) Repository.getValue("projectEnvelopeTemplateId");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        //TemplateApprovalStage
        TemplateApprovalStage templateApprovalStage = new TemplateApprovalStage();
        templateApprovalStage.setStage(2);
        templateApprovalStage.setApproveAll(false);
        templateApprovalStage.setEnvelopeTemplateId(envelopeTemplateId);
        templateApprovalStage.setSecurityRoleId(securityRoleId);
        //Main
        ProjectEnvelopeTemplateWithTemplateApprovalStage projectEnvelopeTemplateWithTemplateApprovalStage = new ProjectEnvelopeTemplateWithTemplateApprovalStage();
        projectEnvelopeTemplateWithTemplateApprovalStage.setProjectOrAccountEnvelopetemplateId(projectOrAccountEnvelopetemplateId);
        projectEnvelopeTemplateWithTemplateApprovalStage.setTemplateType("ProjectEnvelopeTemplate");
        projectEnvelopeTemplateWithTemplateApprovalStage.setTemplateApprovalStage(templateApprovalStage);
        Gson gson = new Gson();
        String json = gson.toJson(projectEnvelopeTemplateWithTemplateApprovalStage);
        response = given().
                body(json).
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "templateapprovalstage/create").then()
                .assertThat().statusCode(201).and().extract().response();
    }
}