package com.org.api.model;

import java.util.Date;

public class Invitation {
    private String message;
    private String email;
    private String mobile;
    private String invitationIdStr;
    private String securityCode;


    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }



    //    private String id;
//    private String teleCode;
//    private String accountPersonDBId;
//    private String position;
//    private Integer roleTypeId;
//    private Integer personId;
//    private Integer statusTypeId;
//
//    private Date issued;
//    private Date expires;
//    private String performedBy;
//    private String performedOn;
//
//    private String agencyId;
//    private String firstName;
//    private String lastName;
//    private String countryABBRCode;
//    private String personalMobile;
//    private String title;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getInvitationIdStr() {
        return invitationIdStr;
    }

    public void setInvitationIdStr(String invitationIdStr) {
        this.invitationIdStr = invitationIdStr;
    }






}