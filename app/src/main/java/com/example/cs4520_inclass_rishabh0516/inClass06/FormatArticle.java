package com.example.cs4520_inclass_rishabh0516.inClass06;
// Rishabh Sahu
// Assignment #6
public class FormatArticle {
    private String author;
    private String title;
    private String description;
    private String urlToImage;
    private String publishedAt;

    public FormatArticle(String author, String title, String description, String urlToImage, String publishedAt) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    public FormatArticle() {
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public String toString() {
        return
                "author: " + author + '\n' +
                "title: " + title + '\n' +
                "description: " + description + '\n' +
                "publishedAt: " + publishedAt;
    }
}
