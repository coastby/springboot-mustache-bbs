package com.mustache.bbs.dto;

import com.mustache.bbs.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinResponse {
    private String userId;
    public static UserJoinResponse from(User user){
        return new UserJoinResponse(user.getUserId());
    }
}
