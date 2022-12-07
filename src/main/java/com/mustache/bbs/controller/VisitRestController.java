package com.mustache.bbs.controller;

import com.mustache.bbs.dto.VisitWriteRequest;
import com.mustache.bbs.dto.VisitWriteResponse;
import com.mustache.bbs.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
