package com.org.api;

import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteDepartment extends CommonLogin {

    @Test
    public void testDeleteDepartments() throws Exception {
        String departmentId = (String) Repository.getValue("departmentId");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        response = given().
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "department/delete/" + departmentId)
                .then().
                        assertThat().statusCode(200).and().extract().response();
    }
}
