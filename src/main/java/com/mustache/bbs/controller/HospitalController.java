package com.mustache.bbs.controller;

import com.mustache.bbs.entity.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import com.mustache.bbs.service.HospitalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {
    HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping(value = "/search")
    public String showSearchList(@RequestParam String keyword, @PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model){
        List<Hospital> hospitals = hospitalService.search(keyword, pageable);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber()+"&keyword="+keyword);
        model.addAttribute("next", pageable.next().getPageNumber()+"&keyword="+keyword);
        return "/hospitals/list";
    }
    @GetMapping(value = "/list")
    public String showList(Model model, @PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Hospital> page = hospitalService.getHospitalList(pageable);
        model.addAttribute("hospitals", page);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "/hospitals/list";
    }
}
