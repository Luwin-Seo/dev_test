package com.example.dev_test.dto;

import com.example.dev_test.model.Article;
import java.sql.Timestamp;

public class ArticleListResponseDto {
    private Long id;
    private String title;
    private int viewCount;
    private boolean isPinned;
    private Timestamp createdDatetime;
    private String image;

    public ArticleListResponseDto() {
    }

    public ArticleListResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.viewCount = article.getViewCount();
        this.isPinned = article.isPinned();
        this.createdDatetime = article.getCreatedDatetime();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getViewCount() {
        return viewCount;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    public String getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
