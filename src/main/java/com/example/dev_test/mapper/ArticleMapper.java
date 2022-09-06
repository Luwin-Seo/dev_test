package com.example.dev_test.mapper;

import com.example.dev_test.model.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleMapper {

    @Insert("INSERT INTO cms__article(board_id, is_pinned, view_count, title, content_html, content_string) VALUES(#{article.boardId}, #{article.isPinned}, #{article.viewCount}, #{article.title}, #{article.contentHtml}, #{article.contentString})")
    int insert(@Param("article")Article article);
}
