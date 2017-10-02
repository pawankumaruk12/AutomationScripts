package com.org.api.model;

public class NewUser {
    private String securityCode;
    private String username;
    private String password;
    private boolean contactByMail;
    private boolean contactByTelephone;
    private boolean contactByEmail;
    private String invitationIdStr;

    public String getInvitationIdStr() {
        return invitationIdStr;
    }
    public void setInvitationIdStr(String invitationIdStr) {
        this.invitationIdStr = invitationIdStr;
    }
    public String getSecurityCode() {
        return securityCode;
    }
    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isContactByMail() {
        return contactByMail;
    }
    public void setContactByMail(boolean contactByMail) {
        this.contactByMail = contactByMail;
    }
    public boolean isContactByTelephone() {
        return contactByTelephone;
    }
    public void setContactByTelephone(boolean contactByTelephone) {
        this.contactByTelephone = contactByTelephone;
    }
    public boolean isContactByEmail() {
        return contactByEmail;
    }
    public void setContactByEmail(boolean contactByEmail) {
        this.contactByEmail = contactByEmail;
    }

}
