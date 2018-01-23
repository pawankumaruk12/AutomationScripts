package com.org.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Person;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreatePerson extends CommonLogin {

    @Test
    public void testPersonCreation() {
        String accountPersonDBId = (String) Repository.getValue("accountPersonDBId");

        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        Integer personCount = 0;
        Integer mobileCount = 0;
        Integer emailCount = 0;

        Person person = new Person();
        person.setFirstName("AutoPerson");
        //person.setMiddleName(null);
        person.setLastName("Name" + personCount + 1);
        person.setAccountPersonDBId(accountPersonDBId);
        person.setTitle("Mrs.");
        person.setGender("F");
        person.setPersonalMobile("077832389239" + mobileCount + 1);
        person.setCountryABBRCode("GBR");
        person.setPersonalEmail("AutoPerson" + emailCount + "@" + "gmail.com");


       ;
        String json = gson.toJson(person);

        response = given().
                body(json).
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "person/create").then()
                .assertThat().statusCode(201).and().extract().response();

        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();

        Person createdPerson = gson.fromJson(fullBody.get(RESULTS).getAsJsonArray().get(0).getAsJsonObject().getAsJsonObject("person").toString(), Person.class);
        JsonObject jsonPerson = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("person");

        Repository.addData("NEW_PERSON", createdPerson);
        String personId = jsonPerson.get("id").getAsString();
        Repository.addData("personId", personId);
        String title = jsonPerson.get("title").getAsString();
        Repository.addData("title", title);
        String firstName = jsonPerson.get("firstName").getAsString();
        Repository.addData("firstName", firstName);
        String lastName = jsonPerson.get("lastName").getAsString();
        Repository.addData("lastName", lastName);
        String personalEmail = jsonPerson.get("personalEmail").getAsString();
        Repository.addData("personalEmail", personalEmail);
        String personalMobile = jsonPerson.get("personalMobile").getAsString();
        Repository.addData("personalMobile", personalMobile);
        String gender = jsonPerson.get("gender").getAsString();
        Repository.addData("gender", gender);
        String teleCode = jsonPerson.get("teleCode").getAsString();
        Repository.addData("teleCode", teleCode);
        String countryABBRCode = jsonPerson.get("countryABBRCode").getAsString();
        Repository.addData("countryABBRCode", countryABBRCode);
        String versionId = jsonPerson.get("versionId").getAsString();
        Repository.addData("versionId", versionId);
    }
}


