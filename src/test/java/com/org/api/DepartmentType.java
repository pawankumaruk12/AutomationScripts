package com.org.api;

import com.org.api.model.Department;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DepartmentType extends CommonLogin {

    @Test
    public void DepartmentTypes() throws Exception {
        String projectTypeId = (String) Repository.getValue("typeId");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        Department department = new Department();


        String json = gson.toJson(department);
        response = given().
                body(json).
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "department/types/" + projectTypeId).then().
                        assertThat().statusCode(200).and().extract().response();
    }

}
