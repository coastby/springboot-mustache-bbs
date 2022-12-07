package com.mustache.bbs.service;

import com.mustache.bbs.dto.UserLoginRequest;
import com.mustache.bbs.dto.UserLoginResponse;
import com.mustache.bbs.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Value("${jwt.token.secretKey}")
    private String secretKey;
    private long expireDate = 1000 * 30 * 60;
    public UserLoginResponse login(UserLoginRequest userLoginRequest){
        String token = JwtUtil.createToken(userLoginRequest.getUserId(), secretKey, expireDate);

        return UserLoginResponse.builder()
                .userId(userLoginRequest.getUserId())
                .token(token)
                .build();
    }
}
