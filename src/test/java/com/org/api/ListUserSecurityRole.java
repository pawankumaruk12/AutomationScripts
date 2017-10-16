package com.org.api;

import com.google.gson.Gson;
import com.org.api.model.PaginationFilter;
import com.org.api.model.Repository;
import com.org.api.model.StandardPagedRequest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ListUserSecurityRole extends CommonLogin {
    @Test
    public void testListUserSecurityRole() throws Exception {
       String userId = (String) Repository.getValue("userId");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        //StandardPageRequest
        StandardPagedRequest standardPagedRequest = new StandardPagedRequest();
        standardPagedRequest.setIncludeLinks(false);
        standardPagedRequest.setNoOfRows(-1);
        standardPagedRequest.setStartPosition(0);
        List <PaginationFilter> filters = new ArrayList<>();
        PaginationFilter filter = new PaginationFilter();
        filter.setColumnName("userId");
        filter.setCondition("=");
        List<String> dataList = new ArrayList<>();
        dataList.add(userId);
        filter.setDataList(dataList);
        filters.add(filter);
        standardPagedRequest.setFilters(filters);

        Gson gson = new Gson();
        String json = gson.toJson(standardPagedRequest);
        response = given().
                body(json).
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken)
                .contentType(ContentType.JSON).
                post(API_PATH + "usersecurityrole/list").then()
                .assertThat().statusCode(200).and().extract().response();
    }
}
