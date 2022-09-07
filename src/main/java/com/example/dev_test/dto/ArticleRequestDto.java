package com.example.dev_test.dto;

public class ArticleRequestDto {
    private Long boardId;
    private String title;
    private String content;

    public ArticleRequestDto() {
    }

    public ArticleRequestDto(Long boardId, String title, String content) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
