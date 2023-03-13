package com.example.cs4520_inclass_rishabh0516.inClass06;

// Rishabh Sahu
// Assignment #6

import java.util.ArrayList;

public class APIResponse {
    private String status;
    private String totalResults;
    private ArrayList<Article> articles;

    public APIResponse() {
    }

    public APIResponse(String status, String totalResults, ArrayList<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status='" + status + '\'' +
                ", totalResults='" + totalResults + '\'' +
                ", articles=" + articles +
                '}';
    }
}
