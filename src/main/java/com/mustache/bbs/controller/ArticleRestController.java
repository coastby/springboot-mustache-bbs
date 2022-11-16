package com.mustache.bbs.controller;

import com.mustache.bbs.dto.ArticleAddRequest;
import com.mustache.bbs.dto.ArticleDto;
import com.mustache.bbs.repository.ArticleRepository;
import com.mustache.bbs.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleRestController {
    ArticleService articleService;

    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable Long id){
        return ResponseEntity.ok().body(articleService.getArticle(id));
    }
    @PostMapping
    public ResponseEntity<ArticleDto> add(@RequestBody ArticleAddRequest articleAddRequest){
        ArticleDto articleDto = articleService.saveArticle(articleAddRequest);
        return ResponseEntity.status(HttpStatus.OK).body(articleDto);
    }

}
