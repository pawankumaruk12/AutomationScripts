package com.org.api;

import com.google.gson.Gson;
import com.org.api.model.*;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import javax.xml.ws.Response;

import static io.restassured.RestAssured.given;

public class CreateInvitation extends CommonLogin {

    @Test
    public void testCreateInvitation() {
        // String accountId = (String) Repository.getValue("accountId");
        String email = (String) Repository.getValue("personalEmail");
        String mobile = (String) Repository.getValue("personalMobile");
        String telecode = (String) Repository.getValue("teleCode");
        String accountPersonDBId = (String) Repository.getValue("accountPersonDBId");
        String firstName = (String) Repository.getValue("firstName");
        String lastName = (String) Repository.getValue("lastName");
        String countryABBRCode = (String) Repository.getValue("countyABBRCode");
        String personalMobile = (String) Repository.getValue("personalMobile");
        String title = (String) Repository.getValue("title");
        String departmentId = (String) Repository.getValue("departmentId");
        String roleTypeId = (String) Repository.getValue("roleTypeId");
        String id = (String) Repository.getValue("personId");


        //Invitation
        Invitation invitation = new Invitation();
        invitation.setEmail(email);
        invitation.setMobile(mobile);
        invitation.setMessage("MEssage");

        //Person
        Person person = new Person();
        person.setId(id);
        person.setLastName(lastName);
        person.setFirstName(firstName);
        person.setAccountPersonDBId(accountPersonDBId);
        person.setClientReference("TestRef");
        person.setCountryABBRCode("GBR");
        person.setPersonalEmail(email);
        person.setPersonalMobile(personalMobile);
        person.setTeleCode(telecode);
        person.setTitle(title);

        //ProjectMember
        ProjectMember projectMember = new ProjectMember();
        projectMember.setProjectEmail(email);

        projectMember.setPosition("Production Manager");
        projectMember.setDepartmentId(departmentId);
        projectMember.setDescription("Art Department Assistant");
        projectMember.setTeleCode(telecode);
        projectMember.setRoleTypeId(roleTypeId);


        Links links = new Links();
        links.setPerson(person);
        links.setProjectMember(projectMember);

        //  MAIN!!!
        InvitationWithLinks invitationWithLinks = new InvitationWithLinks();
        invitationWithLinks.setInvitation(invitation);
        invitationWithLinks.setLinks(links);

        Gson gson = new Gson();
        String json = gson.toJson(invitationWithLinks);

        String jsessionid = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");

        response = given().
                body(json).
                when()
                .cookie("JSESSIONID", jsessionid)
                .cookie("XSRF-TOKEN", xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "invitation/create").then()
                 .assertThat().statusCode(201).and().extract().response();

      }


}



