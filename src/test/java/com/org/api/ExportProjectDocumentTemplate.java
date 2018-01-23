package com.org.api;

import com.org.api.model.ProjectEnvelopeTemplate;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ExportProjectDocumentTemplate extends CommonLogin {
    @Test
    public void testExportProjectDocumentTemplate() throws Exception {
        String projectEnvelopeTemplateId = (String) Repository.getValue("projectEnvelopeTemplateId");

        ProjectEnvelopeTemplate projectEnvelopeTemplate = new ProjectEnvelopeTemplate();
        projectEnvelopeTemplate.setId(projectEnvelopeTemplateId);


        String jsonTemp = gson.toJson(projectEnvelopeTemplate);

        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);

        response = given().
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken)
                .contentType(ContentType.JSON).
                post(API_PATH + "projectenvelopetemplate/export/" + projectEnvelopeTemplateId).then()
                .assertThat().statusCode(200).and().extract().response();
    }
}
