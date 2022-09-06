package com.example.dev_test.dto;

import java.time.LocalDateTime;

public class ArticleListResponseDto {
    private Long id;
    private String title;
    private int viewCount;
    private boolean isPinned;
    private LocalDateTime createdDatetime;

    public ArticleListResponseDto(Long id,
                              String title,
                              int viewCount,
                              boolean isPinned,
                              LocalDateTime createdDatetime) {
        this.id = id;
        this.title = title;
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

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }
}
