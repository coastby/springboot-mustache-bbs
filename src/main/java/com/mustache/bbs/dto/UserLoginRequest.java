package com.mustache.bbs.dto;

import com.mustache.bbs.domain.User;
import com.mustache.bbs.domain.UserRole;
import lombok.Getter;

@Getter
public class UserLoginRequest {
    private String userId;
    private String password;

}
