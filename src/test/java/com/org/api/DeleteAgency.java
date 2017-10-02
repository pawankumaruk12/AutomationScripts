package com.org.api;


import com.org.api.unittest.AgencyService;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteAgency extends CommonLogin {
    public static String AGENCY_ID;

    @Test(enabled = false)
    public void DeleteAgencys() throws Exception {
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        AGENCY_ID = AgencyService.getLastAgencyId(null, jsessionId, xsrfToken);
        response = given().when().cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).contentType(ContentType.JSON)
                .post(API_PATH + "agency/delete/" + AGENCY_ID).then().
                        assertThat().statusCode(200).and().extract().response();

    }

}