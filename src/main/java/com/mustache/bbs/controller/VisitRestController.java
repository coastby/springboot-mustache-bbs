package com.mustache.bbs.controller;

import com.mustache.bbs.dto.VisitResponse;
import com.mustache.bbs.dto.VisitWriteRequest;
import com.mustache.bbs.dto.VisitWriteResponse;
import com.mustache.bbs.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitRestController {
    private final VisitService visitService;

    @PostMapping
    public ResponseEntity<VisitWriteResponse> write(@RequestBody VisitWriteRequest visitWriteRequest, Authentication authentication){
        String userId = authentication.getName();
        VisitWriteResponse visitWriteResponse = visitService.write(visitWriteRequest, userId);
        return ResponseEntity.ok().body(visitWriteResponse);
    }
    @GetMapping
    public ResponseEntity<List<VisitResponse>> showVisitList(){
        List<VisitResponse> responses = visitService.showVisitList();
        return ResponseEntity.ok().body(responses);
    }
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<List<VisitResponse>> showVisitsOfUser(@PathVariable Long id){
        List<VisitResponse> responses = visitService.showVisitsOfUser(id);
        return ResponseEntity.ok().body(responses);
    }
    @GetMapping(value = "/hospitals/{id}")
    public ResponseEntity<List<VisitResponse>> showVisitorsOfHospital(@PathVariable Integer id){
        List<VisitResponse> responses = visitService.showVisitorsOfHospital(id);
        return ResponseEntity.ok().body(responses);
    }
}
