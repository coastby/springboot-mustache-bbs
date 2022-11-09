package com.mustache.bbs.controller;

import com.mustache.bbs.dto.ArticleDto;
import com.mustache.bbs.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
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
            model.addAttribute("message", String.format("%d번 게시글이 없습니다.", id));
            return "articles/error";
        }
    }
    @GetMapping("")
    public String index(){
        return "redirect:/articles/list";
    }
    @GetMapping("/list")
    public String list(Model model){
        List<Article> articleList = articleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("articleList", articleList);
        return "articles/list";
    }
    @GetMapping(value = "/{id}/edit")
    public String editArticle(@PathVariable long id, Model model){
        Optional<Article> articleOpt = articleRepository.findById(id);
        if(!articleOpt.isEmpty()){
            model.addAttribute("article", articleOpt.get());
            return "/articles/edit";
        } else {
            model.addAttribute("message", String.format("%d번 게시글이 없습니다.", id));
            return "error";
        }
    }
    @PostMapping("/update")
    public String update(ArticleDto articleDto){
        log.info("{}, {}", articleDto.getTitle(), articleDto.getContent());
        Article article = articleRepository.save(articleDto.toEntity());
        return String.format("redirect:/articles/%d", article.getId());
    }

    @GetMapping(value = "/{id}/delete")
    public String delete(@PathVariable Long id){
        articleRepository.deleteById(id);
        return "redirect:/articles/list";
    }
}
