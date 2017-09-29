package com.org.api.model;

import java.util.ArrayList;
import java.util.List;
import com.org.api.model.PaginationFilter;
import com.org.api.model.PaginationSorter;


public class StandardPagedRequest {
    /**
     * Start position of rows in the table
     */
    private int startPosition;

    /**
     * Number of rows starting from startPosition
     */
    private int noOfRows;

    private String simpleSearch;

    private List<PaginationFilter> filters = new ArrayList<PaginationFilter>();

    private List <PaginationSorter>  sorts  = new ArrayList<PaginationSorter>();
}
