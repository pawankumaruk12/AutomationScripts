package com.org.api.model;

public class EnvelopeTemplate {
    private String name;
    private String json;
    private Boolean approvalRequired;
    private String type;

    public Boolean getApprovalRequired() {
        return approvalRequired;
    }
    public void setApprovalRequired(Boolean approvalRequired) {
        this.approvalRequired = approvalRequired;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getJson() {
        return json;
    }
    public void setJson(String json) {
        this.json = json;
    }
}
