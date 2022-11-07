package com.mustache.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MustacheController {
    @GetMapping(value = "/hi")      //Endpoint Mapping
    public String mustacheCon(Model model) {
        model.addAttribute("username", "hoon");     //view에 값을 넘긴다
        return "greetings";     //greetings라는 이름의 view를 리턴
    }

    @GetMapping(value = "/hi/{id}")
    public String mustacheConById(Model model, @PathVariable String id) {
        model.addAttribute("username", id);
        return "greetings";
    }
}
