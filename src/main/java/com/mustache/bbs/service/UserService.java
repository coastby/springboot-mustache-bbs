package com.mustache.bbs.service;

import com.mustache.bbs.domain.User;
import com.mustache.bbs.dto.UserJoinRequest;
import com.mustache.bbs.dto.UserJoinResponse;
import com.mustache.bbs.dto.UserLoginRequest;
import com.mustache.bbs.dto.UserLoginResponse;
import com.mustache.bbs.repository.UserRepository;
import com.mustache.bbs.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
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
    //userId로 User를 찾아주는 메서드
    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("해당 아이디가 없습니다."));
    }
    //회원가입
    public UserJoinResponse join(UserJoinRequest userJoinRequest) {
        userRepository.findByUserId(userJoinRequest.getUserId()).ifPresent(user -> {throw new RuntimeException("동일한 아이디가 존재합니다.");});
        //비밀번호를 인코딩해서 DB에 저장
        User user = userJoinRequest.toEntity(encoder.encode(userJoinRequest.getPassword()));
        User saved = userRepository.save(user);
        return UserJoinResponse.from(saved);
    }
}
