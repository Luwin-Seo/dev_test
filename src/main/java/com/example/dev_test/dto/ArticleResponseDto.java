package com.example.dev_test.dto;

import java.time.LocalDateTime;

public class ArticleResponseDto {
    private Long id;
    private String title;
    private String contentHtml;
    private int viewCount;
    private boolean isPinned;
    private LocalDateTime createdDatetime;

    public ArticleResponseDto(Long id,
                              String title,
                              String contentHtml,
                              int viewCount,
                              boolean isPinned,
                              LocalDateTime createdDatetime) {
        this.id = id;
        this.title = title;
        this.contentHtml = contentHtml;
        this.viewCount = viewCount;
        this.isPinned = isPinned;
        this.createdDatetime = createdDatetime;
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

    public LocalDateTime getCreatedDatetime() {
        return createdDatetime;
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
}
