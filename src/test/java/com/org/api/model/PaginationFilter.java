package com.org.api.model;

import java.util.List;

public class PaginationFilter {
    private String columnName;
    private List<? extends Object> dataList;
    private String condition;
    private String tableAlias;
}
