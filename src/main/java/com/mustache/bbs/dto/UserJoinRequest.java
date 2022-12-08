package com.mustache.bbs.dto;

import com.mustache.bbs.domain.User;
import com.mustache.bbs.domain.UserRole;
import lombok.Getter;

@Getter
public class UserJoinRequest {
    private String userId;
    private String password;
    public User toEntity(String pwd){
        return User.builder()
                .userId(this.userId)
                .password(pwd)
                .role(UserRole.USER)
                .build();
    }
}
