package com.org.api.model;

public class UserSecurityRole {
    private String id;
    private String userId;
    private String securityRoleTypeId;
    private String securityItemId;
    private String lastUpdated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSecurityRoleTypeId() {
        return securityRoleTypeId;
    }

    public void setSecurityRoleTypeId(String securityRoleTypeId) {
        this.securityRoleTypeId = securityRoleTypeId;
    }

    public String getSecurityItemId() {
        return securityItemId;
    }

    public void setSecurityItemId(String securityItemId) {
        this.securityItemId = securityItemId;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
