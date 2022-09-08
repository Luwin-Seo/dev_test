package com.example.dev_test;

import com.example.dev_test.dto.ArticleListResponseDto;
import com.example.dev_test.dto.ArticleRequestDto;
import com.example.dev_test.dto.ArticleResponseDto;
import com.example.dev_test.mapper.ArticleMapper;
import com.example.dev_test.mapper.BoardMapper;
import com.example.dev_test.model.Board;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.sql.Timestamp;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArticleIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ArticleMapper aMapper;
    @Autowired
    private BoardMapper bMapper;

    private HttpHeaders headers;
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Order(1)
    @DisplayName("Article 등록")
    void test1() throws JsonProcessingException {

        Board board1 = new Board(null, "자유");
        Board board2 = new Board(null, "고민");
        Board board3 = new Board(null, "자유상상");

        bMapper.insert(board1);
        bMapper.insert(board2);
        bMapper.insert(board3);

        //given

        ArticleRequestDto articleReques1 = new ArticleRequestDto(
                1L,
                "testArticle1",
                "<p>This is a paragraph1.</p><p>This is another paragraph1.</p><img src='1_1.jpg' alt='w3ii.com' width='104' height='142'><img src='1_2.jpg' alt='w3ii.com' width='104' height='142'>");

        ArticleRequestDto articleReques2 = new ArticleRequestDto(
                1L,
                "testArticle2",
                "<p>This is a paragraph2.</p><p>This is another paragraph2.</p><img src='2_1.jpg' alt='w3ii.com' width='104' height='142'><img src='2_2.jpg' alt='w3ii.com' width='104' height='142'>");

        ArticleRequestDto articleReques3 = new ArticleRequestDto(
                2L,
                "testArticle3",
                "<p>This is a paragraph3.</p><p>This is another paragraph3.</p><img src='3_1.jpg' alt='w3ii.com' width='104' height='142'>");

        ArticleRequestDto articleReques4 = new ArticleRequestDto(
                3L,
                "testArticle4",
                "<p>This is a paragraph4.</p><p>This is another paragraph4.</p><img src='4_1.jpg' alt='w3ii.com' width='104' height='142'>");

        String requestBody1 = mapper.writeValueAsString(articleReques1);
        HttpEntity<String> request1 = new HttpEntity<>(requestBody1, headers);

        String requestBody2 = mapper.writeValueAsString(articleReques2);
        HttpEntity<String> request2 = new HttpEntity<>(requestBody2, headers);

        String requestBody3 = mapper.writeValueAsString(articleReques3);
        HttpEntity<String> request3 = new HttpEntity<>(requestBody3, headers);

        String requestBody4 = mapper.writeValueAsString(articleReques4);
        HttpEntity<String> request4 = new HttpEntity<>(requestBody4, headers);

        // when
        ResponseEntity<String> response1 = restTemplate.postForEntity(
                "/board/article",
                request1,
                String.class);

        ResponseEntity<String> response2 = restTemplate.postForEntity(
                "/board/article",
                request2,
                String.class);

        ResponseEntity<String> response3 = restTemplate.postForEntity(
                "/board/article",
                request3,
                String.class);

        ResponseEntity<String> response4 = restTemplate.postForEntity(
                "/board/article",
                request4,
                String.class);

        //then

        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        assertEquals(HttpStatus.OK, response3.getStatusCode());
        assertEquals(HttpStatus.OK, response4.getStatusCode());
    }

    @Test
    @Order(2)
    @DisplayName("Article 목록 조회")
    void test2() throws JsonProcessingException {

        //when
        ResponseEntity<ArticleListResponseDto[]> response = restTemplate.getForEntity(
                "/board/articles",
                ArticleListResponseDto[].class);

        // then

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(4, response.getBody().length);
        assertEquals(1, response.getBody()[0].getId());
        assertEquals("testArticle1", response.getBody()[0].getTitle());
        assertEquals(0, response.getBody()[0].getViewCount());
        assertFalse(response.getBody()[0].isPinned());
        assertNotNull(response.getBody()[0].getCreatedDatetime());
        assertEquals("1_1.jpg", response.getBody()[0].getImage());

        assertEquals(2, response.getBody()[1].getId());
        assertEquals("testArticle2", response.getBody()[1].getTitle());
        assertEquals(0, response.getBody()[1].getViewCount());
        assertFalse(response.getBody()[1].isPinned());
        assertNotNull(response.getBody()[1].getCreatedDatetime());
        assertEquals("2_1.jpg", response.getBody()[1].getImage());

        assertEquals(3, response.getBody()[2].getId());
        assertEquals("testArticle3", response.getBody()[2].getTitle());
        assertEquals(0, response.getBody()[2].getViewCount());
        assertFalse(response.getBody()[2].isPinned());
        assertNotNull(response.getBody()[2].getCreatedDatetime());
        assertEquals("3_1.jpg", response.getBody()[2].getImage());

        assertEquals(4, response.getBody()[3].getId());
        assertEquals("testArticle4", response.getBody()[3].getTitle());
        assertEquals(0, response.getBody()[3].getViewCount());
        assertFalse(response.getBody()[3].isPinned());
        assertNotNull(response.getBody()[3].getCreatedDatetime());
        assertEquals("4_1.jpg", response.getBody()[3].getImage());
    }

    @Test
    @Order(3)
    @DisplayName("Article 세부 조회")
    void test3() throws JsonProcessingException {

        //when
        ResponseEntity<ArticleResponseDto> response = restTemplate.getForEntity(
                "/board/article/1",
                ArticleResponseDto.class);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getId());
        assertEquals("testArticle1", response.getBody().getTitle());
        assertEquals("<p>This is a paragraph1.</p><p>This is another paragraph1.</p><img src='1_1.jpg' alt='w3ii.com' width='104' height='142'><img src='1_2.jpg' alt='w3ii.com' width='104' height='142'>", response.getBody().getContentHtml());
        assertEquals(1, response.getBody().getViewCount());
        assertFalse(response.getBody().isPinned());
        assertNotNull(response.getBody().getCreatedDatetime());
        assertEquals(2, response.getBody().getImgs().size());
        assertEquals("1_1.jpg", response.getBody().getImgs().get(0));
        assertEquals("1_2.jpg", response.getBody().getImgs().get(1));
        assertEquals("자유", response.getBody().getBoardName());
    }

    @Test
    @Order(4)
    @DisplayName("Article 세부 조회시 view_count 업데이트")

    void test4() throws JsonProcessingException {

        //when
        ResponseEntity<ArticleResponseDto> response = restTemplate.getForEntity(
                "/board/article/1",
                ArticleResponseDto.class);

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getViewCount());

        int count = response.getBody().getViewCount();

        for (int i = 0; i < 10; i++) {
            ResponseEntity<ArticleResponseDto> responseLoop = restTemplate.getForEntity(
                    "/board/article/1",
                    ArticleResponseDto.class);
            count++;
            assertEquals(count, responseLoop.getBody().getViewCount());
        }
    }

    @Test
    @Order(5)
    @DisplayName("Article 게시판 명으로 검색")
    void test5() throws JsonProcessingException {

        //when
        String qString = "자유";
        ResponseEntity<ArticleListResponseDto[]> response = restTemplate.getForEntity(
                "/board/articles/search?boardName=" + qString,
                ArticleListResponseDto[].class);

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, response.getBody().length);
        if (response.getBody().length == 3) {
            for(ArticleListResponseDto responseDto : response.getBody()) {
                String boardName = bMapper.getById(aMapper.getById(responseDto.getId()).getBoardId()).getNameKo();
                assertTrue(boardName.contains(qString));
            }
        }
    }

    @Test
    @Order(6)
    @DisplayName("Article 날짜로 검색")
    void test6() throws JsonProcessingException {

        //given

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);
        String start = startDate.toString();
        String end = endDate.toString();
        Timestamp startTime = Timestamp.valueOf(startDate.atStartOfDay());
        Timestamp endTime = Timestamp.valueOf(endDate.atStartOfDay());


        //when
        ResponseEntity<ArticleListResponseDto[]> response = restTemplate.getForEntity(
                "/board/articles/period?start=" + start + "&end=" + end,
                ArticleListResponseDto[].class);
        if (response.getBody().length != 0) {
            for (ArticleListResponseDto responseDto : response.getBody()) {
                assertTrue(responseDto.getCreatedDatetime().after(startTime) &&
                        responseDto.getCreatedDatetime().before(endTime) );
            }
        }
    }

    @Test
    @Order(7)
    @DisplayName("Article 삭제")
    void test7() throws JsonProcessingException {

        int before = aMapper.getList().size();
        restTemplate.delete("/board/article?id=1");
        int after = aMapper.getList().size();

        assertEquals(before - 1, after);
    }
}
