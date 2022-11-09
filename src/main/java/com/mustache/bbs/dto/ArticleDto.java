package com.mustache.bbs.dto;

import com.mustache.bbs.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String content;


    public Article toEntity(){
        return new Article(this.id, this.title, this.content);
    }

}
