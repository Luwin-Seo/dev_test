package com.example.dev_test.dto;

import com.example.dev_test.model.Article;

import java.sql.Timestamp;
import java.util.List;

public class ArticleResponseDto {
    private Long id;
    private String title;
    private String contentHtml;
    private int viewCount;
    private boolean isPinned;
    private Timestamp createdDatetime;
    private List<String> imgs;
    private String boardName;

    public ArticleResponseDto(Article article, int viewCount, List<String> imgs, String boardName) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.contentHtml = article.getContentHtml();
        this.viewCount = viewCount;
        this.isPinned = article.isPinned();
        this.createdDatetime = article.getCreatedDatetime();
        this.imgs = imgs;
        this.boardName = boardName;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContentHtml() {
        return contentHtml;
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

    public List<String> getImgs() {
        return imgs;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }
}