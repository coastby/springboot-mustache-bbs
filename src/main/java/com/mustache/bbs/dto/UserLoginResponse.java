package com.mustache.bbs.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLoginResponse {
    private String userId;
    private String token;
}
