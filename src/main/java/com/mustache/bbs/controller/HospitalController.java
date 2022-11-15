package com.mustache.bbs.controller;

import com.mustache.bbs.domain.Hospital;
import com.mustache.bbs.service.HospitalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {
    HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping(value = "/search")
    public String showSearchList(String keyword, @PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model){
        Page<Hospital> hospitals = hospitalService.searchWorkingHospital(keyword, pageable);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber()+"&keyword="+keyword);
        model.addAttribute("next", pageable.next().getPageNumber()+"&keyword="+keyword);
        model.addAttribute("nowPage", pageable.getPageNumber()+1);
        model.addAttribute("totalPage", hospitals.getTotalPages());

        return "hospitals/list";
    }
    @GetMapping(value = "/list")
    public String showList(Model model, @PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Hospital> page = hospitalService.getHospitalList(pageable);
        model.addAttribute("hospitals", page);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("nowPage", pageable.getPageNumber()+1);
        model.addAttribute("totalPage", page.getTotalPages());
        return "hospitals/list";
    }

    @GetMapping(value="/{id}")
    public String shoeHospital(Model model, @PathVariable Integer id){
        Hospital hospital = hospitalService.getHospital(id);
        if(hospital != null){
            model.addAttribute("hospital", hospital);
            LocalDateTime date = hospital.getLicenseDate();
            int year = date.getYear();
            model.addAttribute("licenceYear", year);
            return "hospitals/show";
        } else{
            return "hospitals/error";
        }
    }
    @PostMapping(value="/filter")
    public String filteredList(@RequestParam String address, @RequestParam List<String> types, Model model, @PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Hospital> hospitals = hospitalService.getHospitalByAddressAndType(address, types, pageable);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("nowPage", pageable.getPageNumber()+1);
        model.addAttribute("totalPage", hospitals.getTotalPages());

        return "hospitals/list";
    }

}
