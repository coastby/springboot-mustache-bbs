package com.mustache.bbs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mustache.bbs.dto.ArticleAddRequest;
import com.mustache.bbs.dto.ArticleDto;
import com.mustache.bbs.repository.ArticleRepository;
import com.mustache.bbs.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;      //json 형식으로 만들어준다.
    @MockBean
    ArticleService articleService;

    @Test
    @DisplayName("article을 잘 받아오는지")
    void jsonResponse() throws Exception {
        ArticleDto articleDto = new ArticleDto(1L, "new", "new database");
        given(articleService.getArticle(1L))
                .willReturn(articleDto);
        Long articleId = 1L;

        String url = String.format("/api/v1/articles/%d", articleId);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").value("new"))
                .andDo(print());
        verify(articleService).getArticle(articleId);
    }

    @Test
    @DisplayName("글이 저장되는지")
    void add() throws Exception {
        //Mock 객체에서 특정 메서드가 실행되는 경우 실제 return을 줄 수 없기 때문에 아래와 같이 가정 사항을 만들어준다.
        ArticleAddRequest articleAddRequest = new ArticleAddRequest("제목", "내용");
        given(articleService.saveArticle(any()))
        .willReturn(new ArticleDto(1L, "제목", "내용"));

        Gson gson = new Gson();
        String content = gson.toJson(articleAddRequest);


        mockMvc.perform(post("/api/v1/articles")
//                .content(objectMapper.writeValueAsBytes(new ArticleAddRequest("제목", "내용")))
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
            )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").value("제목"))
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());
        verify(articleService).saveArticle(refEq(articleAddRequest));   //Argument(s) are different! Wanted:에러가 발생하여 refEq() 사용
    }

}