package com.org.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetJsonForDirectHireStartForm extends CommonLogin{
    private static final String GET_API_PATH = "http://192.168.56.139:8080/sdw/modular-forms/";
    @Test
    public void testProjectById() throws Exception {
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        response = given().
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken)
                .contentType(ContentType.JSON)
                .get(GET_API_PATH + "SD_Crew_Direct_Hire_Start_Form.json").then()
                .assertThat().statusCode(200).and().extract().response();

        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();

       String directHireJson = fullBody.toString();
       Repository.addData("json",directHireJson);
    }
}
