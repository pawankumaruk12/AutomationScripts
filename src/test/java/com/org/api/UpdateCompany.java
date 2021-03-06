package com.org.api;

import com.org.api.model.Company;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateCompany extends CommonLogin {

    @Test
    public void testUpdateCompany() throws Exception {
        String accountId = (String) Repository.getValue("accountId");
        String name = (String) Repository.getValue("name");
        String typeId = (String) Repository.getValue("stringTypeId");
        String description = (String) Repository.getValue("description");
        String companyId = (String) Repository.getValue("companyId");
        String registeredNumber = (String) Repository.getValue("registeredNumber");
        String vatNumber = (String) Repository.getValue("vatNumber");
        String vatType = (String) Repository.getValue("vatType");
        String versionId = (String) Repository.getValue("versionId");

        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);

        Company company = new Company();
        company.setId(companyId);
        company.setVatType(vatType);
        company.setVatNumber(vatNumber);
        company.setRegisteredNumber(registeredNumber);
        company.setTypeId(1);
        company.setName(name);
        company.setAccountId(accountId);
        company.setSdCompanyId(companyId);
        company.setVersionId("1");


        String json = gson.toJson(company);

        Response updateResponse = given().
                body(json).
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "company/update").
                        then().
                        assertThat().statusCode(200).and().extract().response();

    }

}
