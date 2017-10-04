package com.org.api.model;

public class EnvelopeTemplate {
    private String name;
    private String json;
    private boolean approvalRequired;
    private String type;

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
    public boolean isApprovalRequired() {
        return approvalRequired;
    }
    public void setApprovalRequired(boolean approvalRequired) {
        this.approvalRequired = approvalRequired;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
