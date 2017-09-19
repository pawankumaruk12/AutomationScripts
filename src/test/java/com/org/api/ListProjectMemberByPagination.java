package com.org.api;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;



public class ListProjectMemberByPagination extends CommonLogin {
    @Test
    public void ListProjectMemberByPaginations() throws IOException {

        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");

        Response res = given().
                body(Files.readAllBytes(Paths.get("src/test/resources/ProjectMemberList.json"))).
                when()
                .cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "projectmember/list").
                        then().assertThat().statusCode(200).and().extract().response();
        String responseString = res.asString();

        JsonPath js = new JsonPath(responseString);
        String ProjectMemberIds = js.get("results[0].projectMember.id");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(HttpStatus.SC_OK, 200);


    }
}
