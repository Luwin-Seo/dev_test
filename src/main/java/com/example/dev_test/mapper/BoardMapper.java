package com.example.dev_test.mapper;

import com.example.dev_test.model.Article;
import com.example.dev_test.model.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("SELECT * FROM cms__board WHERE board_id = #{boardId}")
    @Results({
            @Result(property = "boardId", column = "board_id"),
            @Result(property = "nameKo", column = "name_ko")
    })
    Board getById(@Param("boardId") Long boardId);
}
