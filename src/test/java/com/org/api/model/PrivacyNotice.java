package com.org.api.model;

public class PrivacyNotice {
    private String PNid;
    private String status;
    private String PNversionId;
    private String link;
    private Integer lastUpdated;

    public String getPNid() {
        return PNid;
    }

    public void setPNid(String PNid) {
        this.PNid = PNid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPNversionId() {
        return PNversionId;
    }

    public void setPNversionId(String PNversionId) {
        this.PNversionId = PNversionId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Integer lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
