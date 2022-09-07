package com.example.dev_test.mapper;

import com.example.dev_test.dto.ArticleListResponseDto;
import com.example.dev_test.model.Article;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface ArticleMapper {

    @Insert("INSERT INTO cms__article(board_id, is_pinned, view_count, title, content_html, content_string) " +
            "VALUES(#{article.boardId}, #{article.isPinned}, #{article.viewCount}, #{article.title}, #{article.contentHtml}, #{article.contentString})")
    int insert(@Param("article")Article article);

    @Update("UPDATE cms__article SET view_count = #{viewCount} WHERE article_id = #{id}")
    void updateViewCount(@Param("id") Long id, @Param("viewCount") int viewCount);

    @Select("SELECT * FROM cms__article WHERE article_id = #{id}")
    @Results(id = "ArticleMap", value = {
            @Result(property = "id", column = "article_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "viewCount", column = "view_count"),
            @Result(property = "isPinned", column = "is_pinned"),
            @Result(property = "createdDatetime", column = "created_datetime")
    })
    Article getById(@Param("id") Long id);

    @Select("SELECT * FROM cms__article")
    @ResultMap("ArticleMap")
    List<Article> getList();

    @Delete("DELETE * FROM cms__article WHERE article_id = #{id}")
    void deleteById(@Param("id") Long id);

    @Select("SELECT * FROM cms__article WHERE name_ko = #{boardName}")
    @ResultMap("ArticleMap")
    List<Article> getListByName(@Param("boardName") String boardName);

    @Select("SELECT * FROM cms__article WHERE created_datetime >= #{startTime} AND created_datetime <= #{endTime}")
    @ResultMap("ArticleMap")
    List<Article> getListByCreatedDatetime(@Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);
}
