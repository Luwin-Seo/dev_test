package com.example.dev_test.service;

import com.example.dev_test.dto.ArticleRequestDto;
import com.example.dev_test.mapper.ArticleMapper;
import com.example.dev_test.model.Article;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


@Service
public class ArticleService {

    private final ArticleMapper mapper;

    public ArticleService(ArticleMapper mapper) {
        this.mapper = mapper;
    }

    public void insert(Long id, ArticleRequestDto requestDto) {
        String correctedHtml = HtmlUtils.htmlUnescape(requestDto.getContent());
        Document doc = Jsoup.parse(requestDto.getContent());
        String stringifyContent = doc.text();
        System.out.println(correctedHtml + "교정된 HTML");
        System.out.println(stringifyContent + "문자열로 전환된 HTML");
        Article article = new Article(id, false, 0, requestDto.getTitle(), correctedHtml, stringifyContent);
        mapper.insert(article);
    }
}
