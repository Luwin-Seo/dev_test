package com.example.dev_test.service;

import com.example.dev_test.dto.ArticleListResponseDto;
import com.example.dev_test.dto.ArticleRequestDto;
import com.example.dev_test.dto.ArticleResponseDto;
import com.example.dev_test.mapper.ArticleMapper;
import com.example.dev_test.mapper.BoardMapper;
import com.example.dev_test.model.Article;
import com.example.dev_test.model.Board;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;


@Service
public class ArticleService {

    private final ArticleMapper aMapper;
    private final BoardMapper bMapper;

    public ArticleService(ArticleMapper aMapper, BoardMapper bMapper) {
        this.aMapper = aMapper;
        this.bMapper = bMapper;
    }

    public void insert(Long id, ArticleRequestDto requestDto) {
        String correctedHtml = HtmlUtils.htmlUnescape(requestDto.getContent());
        Document doc = Jsoup.parse(requestDto.getContent());
        String stringifyContent = doc.text();
        System.out.println(correctedHtml + "교정된 HTML");
        System.out.println(stringifyContent + "문자열로 전환된 HTML");
        Article article = new Article(null, id, null,false, 0, requestDto.getTitle(), correctedHtml, stringifyContent);
        aMapper.insert(article);
    }

    @Transactional
    public ArticleResponseDto retrieveArticle(Long id) {
        Article article = aMapper.getById(id);
        String boardName = bMapper.getById(article.getBoardId()).getNameKo();
        Document doc = Jsoup.parse(article.getContentHtml());
        Elements imgs = doc.getElementsByTag("img");
        List<String> stringifyImgs = new ArrayList<>();
        if (imgs.size() > 0) {
            for (Element img : imgs) {
                stringifyImgs.add(img.attr("src"));
            }
        }
        return new ArticleResponseDto(article, stringifyImgs, boardName);
    }

    public List<ArticleListResponseDto> getAll() {
        List<Article> articles= aMapper.getList();
        List<ArticleListResponseDto> responseDtos = new ArrayList<>();
        for (Article article : articles) {
            ArticleListResponseDto responseDto = new ArticleListResponseDto(article);
            Document doc = Jsoup.parse(article.getContentHtml());
            Element img = doc.getElementsByTag("img").first();
            if(img != null) {
                responseDto.setImage(img.attr("src"));
            }

            responseDtos.add(responseDto);
        }
        return responseDtos;
    }
}