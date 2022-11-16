package com.mustache.bbs.service;

import com.mustache.bbs.domain.Article;
import com.mustache.bbs.dto.ArticleDto;
import com.mustache.bbs.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {
    ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    public ArticleDto getArticle(Long id){
        Optional<Article> articleOptional = articleRepository.findById(id);
        ArticleDto articleDto;
        if(articleOptional.isPresent()){
            articleDto = Article.of(articleOptional.get());
        }else {
            articleDto = new ArticleDto(0L, "", "");
        }
        return articleDto;
    }
}
