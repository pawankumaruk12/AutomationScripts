package com.org.api.model;

public class ProjectDocument {

    private String projectId;
    private String documentTypeId;
    private Boolean requiredByPayroll;
    private String id;
    private String versionId;
    private Boolean active;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(String documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public Boolean getRequiredByPayroll() {
        return requiredByPayroll;
    }

    public void setRequiredByPayroll(Boolean requiredByPayroll) {
        this.requiredByPayroll = requiredByPayroll;
    }

}
