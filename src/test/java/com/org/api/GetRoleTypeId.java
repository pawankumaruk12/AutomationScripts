package com.org.api;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.org.api.model.Person;
import com.org.api.model.ProjectMember;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class GetRoleTypeId extends CommonLogin {

    @Test
    public void testRoleTypeId()  {

        String projectId = (String) Repository.getValue("projectId");

        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");

        ProjectMember projectmember = new ProjectMember();

        Gson gson = new Gson();
        String json = gson.toJson(projectmember);

        response = given().
                body(json).
                when()
                .cookie("JSESSIONID",jsessionId)
                .cookie("XSRF-TOKEN",xsrfToken).
                        contentType(ContentType.JSON).
                post(API_PATH + "projectmember/roletypes/" + projectId).then()
                .assertThat().statusCode(200).and().extract().response();

        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();

      //  ProjectMember createdprojectmember = gson.fromJson(fullBody.get("results").getAsJsonArray().get(0).getAsJsonObject().getAsJsonObject("projectmember").toString(), ProjectMember.class);
      //  JsonObject jsonProjectMember = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("projectmember");

       // Repository.addData("NEW_PERSON", createdPerson);
      //  String stringRoleTypeId = jsonProjectMember.get("id").getAsString();
     //   Repository.addData("stringRoleTypeId", stringRoleTypeId);

                String roleTypeId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size()-1).getAsJsonObject().getAsJsonObject().get("id").getAsString();
                Repository.addData("roleTypeId", roleTypeId);

    }
}
