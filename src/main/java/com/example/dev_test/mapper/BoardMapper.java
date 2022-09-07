package com.example.dev_test.mapper;

import com.example.dev_test.model.Article;
import com.example.dev_test.model.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Insert("INSERT INTO cms__board(board_id, name_ko) " +
            "VALUES(#{board.boardId}, #{board.nameKo})")
    int insert(@Param("board") Board board);

    @Select("SELECT * FROM cms__board WHERE board_id = #{boardId}")
    @Results(id = "boardMap", value = {
            @Result(property = "boardId", column = "board_id"),
            @Result(property = "nameKo", column = "name_ko")
    })
    Board getById(@Param("boardId") Long boardId);

    @Select("SELECT * FROM cms__board WHERE name_ko = #{boardName}")
    @ResultMap("boardMap")
    Board getByName(@Param("boardName") String boardName);
}
