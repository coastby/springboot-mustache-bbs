package com.mustache.bbs.controller;

import com.mustache.bbs.entity.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import org.springframework.data.domain.PageRequest;
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
    public String showList(@PathVariable String keyword, Model model){
        List<Hospital> hospitals = hospitalRepository.findByHospitalNameContaining(keyword);
        model.addAttribute("hospitals", hospitals);
        return "/hospitals/list";
    }
}
