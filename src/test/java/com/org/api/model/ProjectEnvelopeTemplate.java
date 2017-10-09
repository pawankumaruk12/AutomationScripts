package com.org.api.model;

public class ProjectEnvelopeTemplate {
    private String projectId;
    private boolean shareAll;
    private String projectEnvelopeTemplateId;
    private String id;
    private boolean active;
    private String templateId;
    private String versionId;
    private String parentId;
    private boolean selfService;

    public boolean isSelfService() {
        return selfService;
    }
    public void setSelfService() {
        this.selfService = selfService;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public String getTemplateId() {
        return templateId;
    }
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
    public String getVersionId() {
        return versionId;
    }
    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }
    public String getParentId() {
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getProjectEnvelopeTemplateId() {
        return projectEnvelopeTemplateId;
    }
    public void setProjectEnvelopeTemplateId(String projectEnvelopeTemplateId) {
        this.projectEnvelopeTemplateId = projectEnvelopeTemplateId;
    }
    public String getProjectId() {
        return projectId;
    }
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    public boolean getShareAll() {
        return shareAll;
    }
    public void setShareAll(boolean shareAll) {
        this.shareAll = shareAll;
    }
}
