package com.mustache.bbs.controller;

import com.mustache.bbs.dto.ArticleDto;
import com.mustache.bbs.repository.ArticleRepository;
import com.mustache.bbs.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {
    @Autowired
    MockMvc mockMvc;
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

}