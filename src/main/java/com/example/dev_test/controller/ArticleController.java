package com.example.dev_test.controller;

import com.example.dev_test.dto.ArticleListResponseDto;
import com.example.dev_test.dto.ArticleRequestDto;
import com.example.dev_test.dto.ArticleResponseDto;
import com.example.dev_test.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/{boardId}/article")
    public ResponseEntity insertArticle (@PathVariable Long boardId, @RequestBody ArticleRequestDto requestDto) {
        articleService.insert(boardId, requestDto);
        return null;
    }

    @GetMapping("/{boardId}/article/{id}")
    public ResponseEntity<ArticleResponseDto> retrieveArticle (@RequestParam Long id) {
        return null;
    }

    @DeleteMapping("/{boardId}/article/{id}")
    public ResponseEntity deleteArticle (@PathVariable Long id) {
        return null;
    }

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleListResponseDto>> getList () {
        return null;
    }
}
