package com.mustache.bbs.controller;

import com.mustache.bbs.dto.UserJoinRequest;
import com.mustache.bbs.dto.UserJoinResponse;
import com.mustache.bbs.dto.UserLoginRequest;
import com.mustache.bbs.dto.UserLoginResponse;
import com.mustache.bbs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest){

    }
    @PostMapping(value = "/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest){
        UserLoginResponse userResponse = userService.login(userLoginRequest);
        return ResponseEntity.ok().body(userResponse);
    }

}