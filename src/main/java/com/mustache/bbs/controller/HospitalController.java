package com.mustache.bbs.controller;

import com.mustache.bbs.entity.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {
    HospitalRepository hospitalRepository;

    public HospitalController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @GetMapping(value = "/list/{keyword}")
    public String showSearchList(@PathVariable String keyword, Model model){
        List<Hospital> hospitals = hospitalRepository.findByHospitalNameContaining(keyword);
        model.addAttribute("hospitals", hospitals);
        return "/hospitals/list";
    }
    @GetMapping(value = "/list")
    public String showList(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Hospital> page = hospitalRepository.findAll(pageable);
        model.addAttribute("hospitals", page);
        return "/hospitals/list";
    }
}
