package com.mustache.bbs.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Slf4j
public class JwtUtil {
    //key를 만드는 메서드
    public static Key makeKey(String key){
        return Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }

    //token 발행하는 메서드
    public static String createToken(String userId, String key, long expiredTimeMs){
        Claims claims = Jwts.claims();
        claims.put("userId", userId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiredTimeMs))
                //.signWith(SignatureAlgorithm.HS256, key)        //11버전에서는 deprecated되어서 key를 사용해야 한다.
                .signWith(JwtUtil.makeKey(key))
                .compact();
    }

    //token에서 claim 추출하는 메서드
    public static Claims extractClaims(String token, String key){
        return Jwts.parserBuilder().setSigningKey(JwtUtil.makeKey(key)).build().parseClaimsJwt(token).getBody();
    }
    //유효한 token인지 확인하는 메서드
    public static boolean validateToken (String token, String key){
        try {
            JwtUtil.extractClaims(token, key);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 JWT 토큰입니다.");
        } catch (MalformedJwtException | IllegalArgumentException e) {
            log.error("잘못된 JWT 토큰입니다.");
        } catch (SignatureException e) {
            log.error("잘못된 JWT 서명입니다.");
        }
        return false;
    }
    //userId 꺼내는 메서드
    public static String getUserId(String token, String key){
        return extractClaims(token, key).get("userId", String.class);
    }
}
