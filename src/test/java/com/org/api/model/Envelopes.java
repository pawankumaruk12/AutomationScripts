package com.org.api.model;

import java.util.List;

public class Envelopes {
    private String jsonmodel;
    private String officeOnlyJSONModel;
    private List<String> userManualInput ;
    private String projectMemberId;
    private String name;
    private String dueDate;
    private String envelopeTemplateId;
    private String templateParentType;
    private String templateParentId;

    public String getJsonmodel() {
        return jsonmodel;
    }

    public void setJsonmodel(String jsonmodel) {
        this.jsonmodel = jsonmodel;
    }

    public String getOfficeOnlyJSONModel() {
        return officeOnlyJSONModel;
    }

    public void setOfficeOnlyJSONModel(String officeOnlyJSONModel) {
        this.officeOnlyJSONModel = officeOnlyJSONModel;
    }

    public List<String> getUserManualInput() {
        return userManualInput;
    }

    public void setUserManualInput(List<String> userManualInput) {
        this.userManualInput = userManualInput;
    }

    public String getProjectMemberId() {
        return projectMemberId;
    }

    public void setProjectMemberId(String projectMemberId) {
        this.projectMemberId = projectMemberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getEnvelopeTemplateId() {
        return envelopeTemplateId;
    }

    public void setEnvelopeTemplateId(String envelopeTemplateId) {
        this.envelopeTemplateId = envelopeTemplateId;
    }

    public String getTemplateParentType() {
        return templateParentType;
    }

    public void setTemplateParentType(String templateParentType) {
        this.templateParentType = templateParentType;
    }

    public String getTemplateParentId() {
        return templateParentId;
    }

    public void setTemplateParentId(String templateParentId) {
        this.templateParentId = templateParentId;
    }

}
