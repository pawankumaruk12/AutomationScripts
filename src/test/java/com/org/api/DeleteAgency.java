package com.org.api;


import com.org.api.unittest.AgencyService;
import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteAgency extends CommonLogin {
    public static String AGENCY_ID;

    @Test(enabled = false)
    public void DeleteAgencys() throws Exception {
        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");
        AGENCY_ID = AgencyService.getLastAgencyId(null, jsessionId, xsrfToken);
        response = given().when().cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
                .post(API_PATH + "agency/delete/" + AGENCY_ID);

        AssertJUnit.assertEquals(response.getStatusCode(), 200);

    }

}