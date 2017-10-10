package com.org.api;

import com.google.gson.JsonObject;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public abstract class CommonLogin {

    public static final String API_PATH = "http://192.168.56.139:8080/sdw/rest/";
    private static final String PATH_LOGIN_SUPERUSER = "src/test/resources/loginAsSuperUser.json";
    private static final String PATH_LOGIN_HOD = "src/test/resources/login_hod.json";
    private static final String PATH_LOGIN_PM = "src/test/resources/login_hod.json";
    private static final String PATH_LOGIN_TM = "src/test/resources/login_tm.json";
    private static final String PATH_LOGIN_AGENT = "src/test/resources/login_hod.json";
    public static final String JSESSIONID = "JSESSIONID";
    public static final String XSRF_TOKEN = "XSRF-TOKEN";
    public static final String RESULTS = "results";
    public static final String PASSWORD = "0nBoard!ng12";
    protected Response response = null;

    @BeforeClass
    public void loginAsSuperUser() throws IOException {
        response = login(PATH_LOGIN_SUPERUSER);
    }

    protected void loginAsHOD() throws Exception {
        response = logout();
        response = login(PATH_LOGIN_HOD);
    }

    protected void loginAsProjectManager() throws Exception {
        response = logout();

        response = login(PATH_LOGIN_PM);
    }


    protected void loginAsTeamMember() throws Exception {
        String username = (String) Repository.getValue("username");
        String password = (String) Repository.getValue("password");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);

        JsonObject loginBody = new JsonObject();
        loginBody.addProperty("username", username);
        loginBody.addProperty("password", password);
        String json = loginBody.toString();

        response = logout();

        response = given().
                body(json).
                when().
                contentType(ContentType.JSON).
                post(API_PATH  + "authentication/login").then()
                .assertThat().statusCode(200).and().extract().response();
    }

    protected void loginAsAgent() throws Exception {
        response = logout();

        response = login(PATH_LOGIN_AGENT);
    }

    private Response login(final String loginPath) throws IOException {
        return given().
                body(Files.readAllBytes(Paths.get(loginPath))).
                when().
                contentType(ContentType.JSON).
                post(API_PATH + "authentication/login").then()
                .assertThat().statusCode(200).and().extract().response();
    }

    private Response logout() {
        return given().
                when().
                contentType(ContentType.JSON).
                post(API_PATH  + "authentication/logout").then()
                .assertThat().statusCode(200).and().extract().response();
    }
}