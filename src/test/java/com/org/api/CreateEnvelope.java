package com.org.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Envelopes;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class CreateEnvelope extends CommonLogin {
    @Test public void testCreateEnvelope() throws Exception {

        String projectMemberId = (String) Repository.getValue("projectMemberId");
        String name = (String) Repository.getValue("envelopeTemplateName");
        String envelopeTemplateId =(String) Repository.getValue("envelopeTemplateId");
        String parentId = (String ) Repository.getValue("departmentEnvelopeTemplateParentId");

        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);

        //Envelopes
        Envelopes envelopes = new Envelopes();
        envelopes.setJsonmodel(new String(Files.readAllBytes(Paths.get("src/test/resources/envelope/json_model.json"))));
        envelopes.setDueDate("1508108400000");
        envelopes.setEnvelopeTemplateId(envelopeTemplateId);
        envelopes.setName(name);
        envelopes.setOfficeOnlyJSONModel("{}");
        envelopes.setProjectMemberId(projectMemberId);
        envelopes.setTemplateParentId(parentId);
        envelopes.setTemplateParentType("DepartmentEnvelopeTemplate");
        envelopes.setUserManualInput(Arrays.asList("productionTitle", "productionCompany", "salaryHolidayEnt"));
        String json = gson.toJson(envelopes);

        response  = given().
                body(json).
                when()
                .cookie(JSESSIONID,jsessionId)
                .cookie(XSRF_TOKEN,xsrfToken)
                .contentType(ContentType.JSON).
                post(API_PATH + "envelope/create");
        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();
        response.getBody().print();
    }
}
