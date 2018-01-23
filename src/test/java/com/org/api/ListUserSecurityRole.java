package com.org.api;

import com.org.api.model.PaginationFilter;
import com.org.api.model.Repository;
import com.org.api.model.StandardPagedRequest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class ListUserSecurityRole extends CommonLogin {
    @Test
    public void testListUserSecurityRole() throws Exception {
       String userId = (String) Repository.getValue("userId");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);

        StandardPagedRequest pagedRequest = StandardPagedRequest.defaultPageRequest();
        PaginationFilter filter = new PaginationFilter();
        filter.setColumnName("userId");
        filter.setCondition("=");
        filter.setDataList(Arrays.asList(userId));
        pagedRequest.setFilters(Arrays.asList(filter));

        String json = gson.toJson(pagedRequest);
        response = given().
                body(json).
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken)
                .contentType(ContentType.JSON).
                post(API_PATH + "usersecurityrole/list").then()
                .assertThat().statusCode(HttpStatus.SC_OK).and().extract().response();
    }
}
