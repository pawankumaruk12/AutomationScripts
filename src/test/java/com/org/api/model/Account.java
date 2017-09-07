package com.org.api.model;

public class Account {

    private String name;
    private String description;
    private Integer typeId;
    private Integer accountId;
    private String accountPersonDBId;
    private String stringAccountId;

    public String getStringTypeId() {
        return stringTypeId;
    }

    public void setStringTypeId(String stringTypeId) {
        this.stringTypeId = stringTypeId;
    }

    private String stringTypeId;

    public String getStringAccountId() {
        return stringAccountId;
    }

    public void setStringAccountId(String stringAccountId) {
        this.stringAccountId = stringAccountId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountPersonDBId() {
        return accountPersonDBId;
    }

    public void setAccountPersonDBId(String accountPersonDBId) {
        this.accountPersonDBId = accountPersonDBId;
    }



}
