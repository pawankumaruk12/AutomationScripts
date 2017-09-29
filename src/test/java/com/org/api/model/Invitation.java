package com.org.api.model;

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