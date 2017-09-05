package com.org.api.model;

public class Department {
    private String name;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    private String projectId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepartmentTypeId() {
        return departmentTypeId;
    }

    public void setDepartmentTypeId(Integer departmentTypeId) {
        this.departmentTypeId = departmentTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Integer departmentTypeId;
    private String description;
}
