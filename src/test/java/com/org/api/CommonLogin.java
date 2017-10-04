package com.org.api;

import com.google.gson.Gson;
import com.org.api.model.NewUser;
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
    private static final String PATH_LOGIN_SUPERUSER = "src/test/resources/login.json";
    private static final String PATH_LOGIN_HOD = "src/test/resources/login_hod.json";
    private static final String PATH_LOGIN_PM = "src/test/resources/login_hod.json";
    private static final String PATH_LOGIN_TM = "src/test/resources/login_tm.json";
    private static final String PATH_LOGIN_AGENT = "src/test/resources/login_hod.json";
    public static final String JSESSIONID = "JSESSIONID";
    public static final String XSRF_TOKEN = "XSRF-TOKEN";
    public static final String RESULTS = "results";
    protected Response response = null;
    @BeforeClass
    public void login() throws IOException {

        response = given().
                body(Files.readAllBytes(Paths.get(PATH_LOGIN_SUPERUSER))).
                when().
                contentType(ContentType.JSON).
                post(API_PATH + "authentication/login");
    }

    protected void loginAsHOD() throws Exception {
        response = given().
                when().
                contentType(ContentType.JSON).
                post(API_PATH  + "authentication/logout");

        response = given().
                body(Files.readAllBytes(Paths.get(PATH_LOGIN_HOD))).
                when().
                contentType(ContentType.JSON).
                post(API_PATH  + "authentication/login");
    }

    protected void loginAsProjectManager() throws Exception {
        response = given().
                when().
                contentType(ContentType.JSON).
                post(API_PATH  + "authentication/logout");

        response = given().
                body(Files.readAllBytes(Paths.get(PATH_LOGIN_PM))).
                when().
                contentType(ContentType.JSON).
                post(API_PATH  +"authentication/login");
    }

    protected void loginAsTeamMember() throws Exception {
        String username = (String) Repository.getValue("username");
        String password = (String) Repository.getValue("password");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);

        NewUser newUser = new NewUser();
        newUser.setUsername(username);
        newUser.setPassword(password);

        Gson gson = new Gson();
        String json = gson.toJson(newUser);

        response = given().
                when().
                cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH  + "authentication/logout");

        response = given().
                body(json).
                when().
                contentType(ContentType.JSON).
                post(API_PATH  + "authentication/login");
    }

    protected void loginAsAgent() throws Exception {
        response = given().
                when().
                contentType(ContentType.JSON).
                post(API_PATH  + "authentication/logout");

        response = given().
                body(Files.readAllBytes(Paths.get(PATH_LOGIN_AGENT))).
                when().
                contentType(ContentType.JSON).
                post(API_PATH  + "authentication/login");
    }
}
