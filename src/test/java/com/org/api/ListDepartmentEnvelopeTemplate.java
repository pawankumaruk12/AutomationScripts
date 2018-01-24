package com.org.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.PaginationFilter;
import com.org.api.model.Repository;
import com.org.api.model.StandardPagedRequest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class ListDepartmentEnvelopeTemplate extends CommonLogin {

    @Test
    public void testListDepartmentEnvelopeTemplate() throws Exception {

        String projectId = (String) Repository.getValue("departmentId");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        //StandardPageRequest

        StandardPagedRequest pagedRequest = StandardPagedRequest.defaultPageRequest();

        PaginationFilter filter = new PaginationFilter();
        filter.setColumnName("departmentId");
        filter.setCondition("=");
        filter.setDataList(Arrays.asList(projectId));

        pagedRequest.setFilters(Arrays.asList(filter));


        pagedRequest.setSorts(new ArrayList<>());


        String json = gson.toJson(pagedRequest);
        response = given().
                body(json).
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken)
                .contentType(ContentType.JSON)
                .post(API_PATH + "departmentenvelopetemplate/list").then()
                .assertThat().statusCode(200).and().extract().response();
        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();
       String departmentEnvelopeTemplateParentId = fullBody.get(RESULTS).getAsJsonArray().get(0).getAsJsonObject().get("departmentEnvelopeTemplate").getAsJsonObject().get("parentId").getAsString();
       Repository.addData("departmentEnvelopeTemplateParentId",departmentEnvelopeTemplateParentId);
    }
}


