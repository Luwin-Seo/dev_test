package com.example.dev_test.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Article {
    private Long id;
    private Long boardId;
    private Timestamp createdDatetime;
    private boolean isPinned;
    private int viewCount;
    private String title;
    private String contentHtml;
    private String contentString;

    public Article(Long id, Long boardId, Timestamp createdDatetime, boolean isPinned, int viewCount, String title, String contentHtml, String contentString) {
        this.id = id;
        this.boardId = boardId;
        this.createdDatetime = createdDatetime;
        this.isPinned = isPinned;
        this.viewCount = viewCount;
        this.title = title;
        this.contentHtml = contentHtml;
        this.contentString = contentString;
    }

    public Long getId() {
        return id;
    }

    public Long getBoardId() {
        return boardId;
    }

    public Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public int getViewCount() {
        return viewCount;
    }

    public String getTitle() {
        return title;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public String getContentString() {
        return contentString;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public void setContentString(String contentString) {
        this.contentString = contentString;
    }
}
