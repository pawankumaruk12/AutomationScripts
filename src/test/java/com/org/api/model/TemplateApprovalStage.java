package com.org.api.model;

public class TemplateApprovalStage {
    private Integer stage;
    private String securityRoleId;
    private boolean approveAll;
    private String envelopeTemplateId;

    public Integer getStage() {
        return stage;
    }
    public void setStage(Integer stage) {
        this.stage = stage;
    }
    public String getSecurityRoleId() {
        return securityRoleId;
    }
    public void setSecurityRoleId(String securityRoleId) {
        this.securityRoleId = securityRoleId;
    }
    public boolean getApproveAll() { return approveAll;
    }
    public void setApproveAll(boolean approveAll) {
        this.approveAll = approveAll;
    }
    public String getEnvelopeTemplateId() {
        return envelopeTemplateId;
    }
    public void setEnvelopeTemplateId(String envelopeTemplateId) {
        this.envelopeTemplateId = envelopeTemplateId;
    }
}