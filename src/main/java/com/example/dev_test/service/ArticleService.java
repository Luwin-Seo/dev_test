package com.example.dev_test.service;

import com.example.dev_test.dto.ArticleListResponseDto;
import com.example.dev_test.dto.ArticleRequestDto;
import com.example.dev_test.dto.ArticleResponseDto;
import com.example.dev_test.dto.DatetimeRequestDto;
import com.example.dev_test.mapper.ArticleMapper;
import com.example.dev_test.mapper.BoardMapper;
import com.example.dev_test.model.Article;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public ResponseEntity<String> insert(ArticleRequestDto requestDto) {
        Document doc = Jsoup.parse(requestDto.getContent());
        String stringifyContent = doc.text();
        System.out.println(stringifyContent + "문자열로 전환된 HTML");
        Article article = new Article(
                null,
                requestDto.getBoardId(),
                null,
                false,
                0,
                requestDto.getTitle(),
                requestDto.getContent(),
                stringifyContent);
        int successNum = aMapper.insert(article) ;
        return new ResponseEntity<>(successNum + "개 레코드가 성공적으로 저장되었습니다", HttpStatus.OK);
    }

    @Transactional
    public ArticleResponseDto retrieveArticle(Long id) {
        Article article = aMapper.getById(id);
        int viewCountUpdate = article.getViewCount()+1;
        aMapper.updateViewCount(id,viewCountUpdate);
        String boardName = bMapper.getById(article.getBoardId()).getNameKo();
        Document doc = Jsoup.parse(article.getContentHtml());
        Elements imgs = doc.getElementsByTag("img");
        List<String> stringifyImgs = new ArrayList<>();
        if (imgs.size() > 0) {
            for (Element img : imgs) {
                stringifyImgs.add(img.attr("src"));
            }
        }
        return new ArticleResponseDto(article, viewCountUpdate, stringifyImgs, boardName);
    }

    public List<ArticleListResponseDto> dtosMaker(List<Article> articles) {
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

    public List<ArticleListResponseDto> getAllArticles() {
        return dtosMaker(aMapper.getList());
    }

    public ResponseEntity<String> deleteArticle(Long id) {
        aMapper.deleteById(id);
        return new ResponseEntity<>("정상적으로 삭제되었습니다", HttpStatus.OK);
    }

    public List<ArticleListResponseDto> getArticlesByName(String boardName) {
        return dtosMaker(aMapper.getListByName(boardName));
    }

    public List<ArticleListResponseDto> getArticlesByDatetime(DatetimeRequestDto requestDto) {
        return dtosMaker(aMapper.getListByCreatedDatetime(requestDto.getStartTime(), requestDto.getEndTime()));
    }
}