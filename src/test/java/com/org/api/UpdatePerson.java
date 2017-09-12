package com.org.api;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

import com.google.gson.Gson;
import com.org.api.model.Person;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.Assert;
import org.testng.annotations.Test;


public class UpdatePerson extends CommonLogin {
    @Test
    public void testUpdatePersons() throws Exception {
       // Person createdPerson = (Person) Repository.getValue("NEW_PERSON");

        String id = (String) Repository.getValue("personId");
        String accountPersonDBId = (String) Repository.getValue("accountPersonDBId");
        String title = (String) Repository.getValue("title");
        String firstName = (String) Repository.getValue("firstName");
        String lastName = (String) Repository.getValue("lastName");
        String middleName = (String) Repository.getValue("middleName");
        String personalEmail = (String) Repository.getValue("personalEmail");
        String personalMobile = (String) Repository.getValue("personalMobile");
        String gender = (String) Repository.getValue("gender");
        String teleCode = (String) Repository.getValue("teleCode");
        String countryABBRCode = (String) Repository.getValue("countryABBRCode");
        String versionId = (String) Repository.getValue("versionId");
        String agencyId = (String) Repository.getValue("agencyId");
        String personTypeId = (String) Repository.getValue("personTypeId");

        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");

        Person person = new Person();

        person.setId(id);
        person.setTitle(title);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setMiddleName(null);
        person.setPersonalEmail(personalEmail);
        person.setPersonalMobile(personalMobile);
        person.setCountryABBRCode(countryABBRCode);
        person.setGender(gender);
        person.setAccountPersonDBId(accountPersonDBId);
        person.setTeleCode(teleCode);
        person.setAgencyId(1);
        person.setVersionId(versionId);
        person.setPersonTypeId(null);
        person.setAgencyId(null);
        person.setClientReference(null);
       // person.getMiddleName(middleName);


        Gson gson = new Gson();
        String json = gson.toJson(person);

        response = given()
                .body(json).
                        when()
                .cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).
                        contentType(ContentType.JSON)
                .post(API_PATH + "person/update");

        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);

    }

}
