package com.org.api.model;

public class ProjectEnvelopeTemplateWithTemplateApprovalStage {
    private String templateType;
    private String projectOrAccountEnvelopetemplateId;
    private TemplateApprovalStage templateApprovalStage;

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getProjectOrAccountEnvelopetemplateId() {
        return projectOrAccountEnvelopetemplateId;
    }

    public void setProjectOrAccountEnvelopetemplateId(String projectOrAccountEnvelopetemplateId) {
        this.projectOrAccountEnvelopetemplateId = projectOrAccountEnvelopetemplateId;
    }

    public TemplateApprovalStage getTemplateApprovalStage() {
        return templateApprovalStage;
    }

    public void setTemplateApprovalStage(TemplateApprovalStage templateApprovalStage) {
        this.templateApprovalStage = templateApprovalStage;
    }
}