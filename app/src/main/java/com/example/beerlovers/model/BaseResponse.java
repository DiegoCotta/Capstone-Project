package com.example.beerlovers.model;

public class BaseResponse<T> {
    private int currentPage;
    private int numberOfPages;
    private int totalResults;
    T data;
    private String status;

    public int getCurrentPage() {
        return currentPage;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
