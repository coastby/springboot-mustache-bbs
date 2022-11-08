package com.mustache.bbs.controller;

import com.mustache.bbs.dto.ArticleDto;
import com.mustache.bbs.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping(value = "/new")
    public String newArticleForm(){
        return "articles/new";
    }
    @PostMapping(value = "/posts")
    public String createArticle(ArticleDto form){
        log.info(form.toString());
        Article article = form.toEntity();
        Article savedArticle = articleRepository.save(article);
        log.info("generated id : " + savedArticle.getId());
        return String.format("redirect:/articles/%d", savedArticle.getId());
    }
    @GetMapping(value = "/{id}")
    public String selectSingle(@PathVariable Long id, Model model) {
        Optional<Article> articleOptional = articleRepository.findById(id);

        if (!articleOptional.isEmpty()) {
            //Optional.get() --> Article
            model.addAttribute("article", articleOptional.get());
            return "articles/show";
        } else {
            return "articles/error";
        }
    }
    @GetMapping("")
    public String index(){
        return "redirect:/article/list";
    }
    @GetMapping("/list")
    public String list(){
        return "list";
    }
}
