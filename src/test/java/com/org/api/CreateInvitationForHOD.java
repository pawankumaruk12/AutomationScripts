package com.org.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.*;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateInvitationForHOD extends CommonLogin {
    @Test
    public void testCreateInvitationForHOD() {

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
        invitation.setMobile(mobile + "1");
        invitation.setMessage("Message for HOD");
        //Person
        Person person = new Person();
        person.setId(id);
        person.setLastName("HOD");
        person.setFirstName("Auto");
        person.setAccountPersonDBId(accountPersonDBId);
        person.setClientReference("TestRef");
        person.setCountryABBRCode("GBR");
        person.setPersonalEmail(firstName + lastName + "HOD" + "@sd.com");
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
        //Links
        Links links = new Links();
        links.setPerson(person);
        links.setProjectMember(projectMember);
        //  MAIN!!!
        InvitationWithLinks invitationWithLinks = new InvitationWithLinks();
        invitationWithLinks.setInvitation(invitation);
        invitationWithLinks.setLinks(links);

        Gson gson = new Gson();
        String json = gson.toJson(invitationWithLinks);
        String jsessionid = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);

        response = given().
                body(json).
                when()
                .cookie(JSESSIONID, jsessionid)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "invitation/create").then()
                .assertThat().statusCode(201).and().extract().response();
        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();
        String invitationIdStr = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("invitation").get("id").getAsString();
        Repository.addData("invitationIdStr", invitationIdStr);
        String projectMemberId = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() -1).getAsJsonObject().getAsJsonObject("links").getAsJsonObject("projectMember").get("id").getAsString();
        Repository.addData("projectMemberId", projectMemberId);
    }
}