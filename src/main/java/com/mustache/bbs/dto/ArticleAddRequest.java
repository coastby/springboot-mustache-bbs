package com.mustache.bbs.dto;

import com.mustache.bbs.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleAddRequest {
    private String title;
    private String content;

    public Article toEntity(){
        return new Article(this.title, this.content);
    }
}
