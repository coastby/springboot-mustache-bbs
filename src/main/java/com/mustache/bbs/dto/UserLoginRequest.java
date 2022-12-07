package com.mustache.bbs.dto;

import lombok.Getter;

@Getter
public class UserLoginRequest {
    private String userId;
    private String password;
}
