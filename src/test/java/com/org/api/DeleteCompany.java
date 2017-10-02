package com.org.api;

import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class DeleteCompany extends CommonLogin {
    @Test
    public void testDeleteCompanys() throws Exception {
        String companyId = (String) Repository.getValue("companyId");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);

        response = given().
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "company/delete/" + companyId).
                        then().
                        assertThat().statusCode(200).and().extract().response();

    }
}


