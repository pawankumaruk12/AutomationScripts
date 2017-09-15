package com.org.api.model;

public class ProjectMember {

    private  String id; //unique=true, nullable=false
    private  String description;; //NotNull, Size( max = 255)
    private  String personId; //NotNull
    private  String departmentId;  //NotNull
    private  String position;  //NotNull
    private  String roleTypeId;  //NotNull
    private  Boolean useit;//NotNull
    private  String projectEmail; //size( max = 120)
    private  String projectMobile; //size( max = 120)
    private  String versionId;
    private String teleCode;
    private String countryABBRCode;
    private String agentPersonId;
    private String agencyId;
    private Boolean active;
    private  String stringRoleTypeId;


    public String getCountryABBRCode() {
        return countryABBRCode;
    }

    public void setCountryABBRCode(String countryABBRCode) {
        this.countryABBRCode = countryABBRCode;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRoleTypeId() {
        return roleTypeId;
    }

    public void setRoleTypeId(String roleTypeId) {
        this.roleTypeId = roleTypeId;
    }

    public Boolean getUseit() {
        return useit;
    }

    public void setUseit(Boolean useit) {
        this.useit = useit;
    }

    public String getProjectEmail() {
        return projectEmail;
    }

    public void setProjectEmail(String projectEmail) {
        this.projectEmail = projectEmail;
    }

    public String getProjectMobile() {
        return projectMobile;
    }

    public void setProjectMobile(String projectMobile) {
        this.projectMobile = projectMobile;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getTeleCode() {
        return teleCode;
    }

    public void setTeleCode(String teleCode) {
        this.teleCode = teleCode;
    }


    public String getAgentPersonId() {
        return agentPersonId;
    }

    public void setAgentPersonId(String agentPersonId) {
        this.agentPersonId = agentPersonId;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getStringRoleTypeId() {
        return stringRoleTypeId;
    }

    public void setStringRoleTypeId(String stringRoleTypeId) {
        this.stringRoleTypeId = stringRoleTypeId;
    }



}
