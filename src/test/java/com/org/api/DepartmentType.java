package com.org.api;

import com.google.gson.Gson;
import com.org.api.model.Department;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DepartmentType extends CommonLogin {

    @Test
    public void DepartmentTypes() throws Exception {

        String projectTypeId = (String) Repository.getValue("typeId");

        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");

        Department department = new Department();

        Gson gson = new Gson();
        String json = gson.toJson(department);


        response = given().

                body(json).
                when()
                .cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "department/types/" + projectTypeId);

        AssertJUnit.assertEquals(response.getStatusCode(), 200);


    }

}
