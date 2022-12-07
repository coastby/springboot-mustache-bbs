package com.mustache.bbs.service;

import com.mustache.bbs.domain.User;
import com.mustache.bbs.dto.UserLoginRequest;
import com.mustache.bbs.dto.UserLoginResponse;
import com.mustache.bbs.repository.UserRepository;
import com.mustache.bbs.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
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

    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("해당 아이디가 없습니다."));
    }
}
