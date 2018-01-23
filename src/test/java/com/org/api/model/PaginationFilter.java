package com.org.api.model;

import java.util.List;

public class PaginationFilter {
    private String columnName;
    private List<? extends Object> dataList;
    private String condition;
    private String tableAlias;

    public String getColumnName() {
        return columnName;
    }
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    public List<? extends Object> getDataList() {
        return dataList;
    }
    public void setDataList(List<? extends Object> dataList) {
        this.dataList = dataList;
    }
    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getTableAlias() {
        return tableAlias;
    }
    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }
}
