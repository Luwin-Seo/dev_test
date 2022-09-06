package com.example.dev_test.model;

public class Board {
    private Long boardId;
    private String nameKo;

    public Board(Long boardId, String nameKo) {
        this.boardId = boardId;
        this.nameKo = nameKo;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getNameKo() {
        return nameKo;
    }
}
