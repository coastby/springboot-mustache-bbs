package com.mustache.bbs.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity     //객체로 인식하도록 한다.
@NoArgsConstructor
@Getter
public class Article {
    @Id
    @GeneratedValue //자동으로 id를 생성한다.
    private Long id;
    private String title;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
