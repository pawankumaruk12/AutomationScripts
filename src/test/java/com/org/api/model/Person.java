package com.org.api.model;

public class Person {


    private String id;
    private String personId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String accountPersonDBId;
    private String personalEmail;
    private String title;
    private String gender;
    private String personalMobile;
    private Integer agencyId;
    private String countryABBRCode;
    private String teleCode;
    private String versionId;
    private String personTypeId;
    private String clientReference;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }



    public String getClientReference() {
        return clientReference;
    }

    public void setClientReference(String clientReference) {
        this.clientReference = clientReference;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getTeleCode() {
        return teleCode;
    }

    public void setTeleCode(String teleCode) {
        this.teleCode = teleCode;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getPersonTypeId() {
        return personTypeId;
    }

    public void setPersonTypeId(String personTypeId) {
        this.personTypeId = personTypeId;
    }



    public String getCountryABBRCode() {
        return countryABBRCode;
    }

    public void setCountryABBRCode(String countryABBRCode) {
        this.countryABBRCode = countryABBRCode;
    }




    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAccountPersonDBId() {
        return accountPersonDBId;
    }

    public void setAccountPersonDBId(String accountPersonDBId) {
        this.accountPersonDBId = accountPersonDBId;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPersonalMobile() {
        return personalMobile;
    }

    public void setPersonalMobile(String personalMobile) {
        this.personalMobile = personalMobile;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

}
