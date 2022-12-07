package com.mustache.bbs.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    //token 발행하는 메서드
    public static String createToken(String userId, String key, long expiredTimeMs){
        Claims claims = Jwts.claims();
        claims.put("userId", userId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiredTimeMs))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}
