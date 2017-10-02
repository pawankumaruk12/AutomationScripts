package com.org.api;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

// Tested and working fine on 17th March 2017
public class PreviewProjectDocumentFromTemplate extends CommonLogin {
    @Test//(enabled = false)
    public void PreviewProjectDocumentsfromTemplate() throws Exception {
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);

        response = given()
                .when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken)
                .get(API_PATH
                        + "projectdocument/template/9wkSlqHAU83iJnYFPj72xw/document/YnqeHtiAOucVRJpHukJYuw/page/1/width/1400/preview/png");

           AssertJUnit.assertEquals(response.getStatusCode(), 200);
    }
}
