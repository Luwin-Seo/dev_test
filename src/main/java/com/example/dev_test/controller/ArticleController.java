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

    @PostMapping("/article")
    public ResponseEntity<String> insertArticle (@RequestBody ArticleRequestDto requestDto) {
        return articleService.insert(requestDto);
    }

    @GetMapping("/article/{id}")
    public ArticleResponseDto retrieveArticle (@PathVariable Long id) {
        return articleService.retrieveArticle(id);
    }

    @GetMapping("/articles")
    public List<ArticleListResponseDto> getAll() {
        return articleService.getAllArticles();
    }

    @DeleteMapping("/article")
    public ResponseEntity<String> deleteArticle(@RequestParam Long id) {
        return articleService.deleteArticle(id);
    }

    @GetMapping("/articles/search")
    public List<ArticleListResponseDto> getArticlesByName(@RequestParam String boardName) {
        return articleService.getArticlesByName(boardName);
    }

    @GetMapping("/articles/period")
    public List<ArticleListResponseDto> getArticlesByDatetime(@RequestParam String start, @RequestParam String end) {
        return articleService.getArticlesByDatetime(start, end);
    }
}
