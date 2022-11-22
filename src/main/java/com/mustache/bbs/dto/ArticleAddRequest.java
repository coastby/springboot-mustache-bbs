package com.mustache.bbs.dto;

import com.mustache.bbs.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
@Data
public class ArticleAddRequest {
    private String title;
    private String content;

    public Article toEntity(){
        return new Article(this.title, this.content);
    }
}
