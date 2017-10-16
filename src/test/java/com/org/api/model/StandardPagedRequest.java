package com.org.api.model;

import java.util.ArrayList;
import java.util.List;


public class StandardPagedRequest {



    private boolean includeLinks;
    private int startPosition;
    private int noOfRows;
    private String simpleSearch;
    private List<PaginationFilter> filters = new ArrayList<PaginationFilter>();
    private List<PaginationSorter> sorts = new ArrayList<PaginationSorter>();

    public boolean isIncludeLinks() {
        return includeLinks;
    }
    public void setIncludeLinks(boolean includeLinks) {
        this.includeLinks = includeLinks;
    }
    public int getStartPosition() {
        return startPosition;
    }
    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }
    public int getNoOfRows() {
        return noOfRows;
    }
    public void setNoOfRows(int noOfRows) {
        this.noOfRows = noOfRows;
    }
    public String getSimpleSearch() {
        return simpleSearch;
    }
    public void setSimpleSearch(String simpleSearch) {
        this.simpleSearch = simpleSearch;
    }
    public List<PaginationFilter> getFilters() {
        return filters;
    }
    public void setFilters(List<PaginationFilter> filters) {
        this.filters = filters;
    }
    public List<PaginationSorter> getSorts() {
        return sorts;
    }
    public void setSorts(List<PaginationSorter> sorts) {
        this.sorts = sorts;
    }
}
