package com.dogsole.developersite.jwt.provider;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


//토큰을 생성하는 클래스
@Component
public class JwtTokenProvider {

    @Value("${jwt.password}")
    private String secretKey; //JWT의 비밀키임: testPassword (프로퍼티에서 꺼내서 임시로 주입해둠)

    //토큰 생성 메서드-------------------------------
    //문자열을 받아 토큰을 생성후 반환.(유저의 id 값을 받아다가 줄거임)
    public String createToken(String subject) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        Date tokenNow = new Date(); //현재 시간(토큰발행시간)을 저장
        Date tokenX = new Date(tokenNow.getTime() + Duration.ofDays(1).toMillis()); // 만료기간 1일
        //                                 Duration 클래스는 시간간격을 나타냄
        //즉 토큰 생성 시간에서 하루를 더한 일자를 저장하고, 이는 토큰의 수명을 의미함.
        String jwtToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) //JWT의 헤더 정보 설정.(토큰의 타입을 나타내며 주로 JWT로 설정함)
                .setIssuer("test") // 토큰발급자 설정 test는 토큰을 발행한 엔터티를 나타냄.
                .setIssuedAt(tokenNow) // JWT 발행 시간을 설정
                .setExpiration(tokenX) // 만료시간(exp)을 설정
                .setSubject(subject) //  토큰 제목(subject)을 설정(토큰의 목적을 나타냄)
                //JWT의 서명을 설정. HS256알고리즘을 사용해 토큰을 서명하며, secretKey를 사용하여 서명키를 설정
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes())) // 알고리즘, 시크릿 키
                .compact(); //jwt를 문자열로 변환하며 반환 (클라이언트가 이 토큰을 받게됨)

        return jwtToken;
    }

    //jwt 토큰의 유효성 체크 메서드--------------------------------
    //JWT를 파싱하고 해당 토큰의 Claims를 추출하는 메서드 (claims는 토큰의 내용을 객체로표현 : 더쉽게 검증하고 조작할 수 있음)
    public Claims parseJwtToken(String token) { //매개변수로 token을 받아 토큰의 내용을 나타내는 Claims객체로 반환
        token = BearerRemove(token); // Bearer 제거 (보통 jwt사용 시 Bearer 뒤 실제 토큰이 오는 경우가 있음)

        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .parseClaimsJws(token)
                .getBody(); //토큰의 내용을 나타내는 Claims객체 반환
    }

    //jwt 토큰의 유효성 체크 메서드--------------------------------
    //JWT를 파싱하고 해당 토큰의 Claims를 추출하는 메서드 (claims는 토큰의 내용을 객체로표현 : 더쉽게 검증하고 조작할 수 있음)
    public boolean parseJwtToken(String token, UserDetails userDetails) { //매개변수로 token을 받아 토큰의 내용을 나타내는 Claims객체로 반환
        Claims claims = parseJwtToken(token);
        String username = claims.getSubject();
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    //토큰의 앞부분인 Bearer 제거 메서드----------------
    private String BearerRemove(String token) {
        return token.substring("Bearer".length());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    @Cacheable("myTokenCache")
    public String generateToken(String userName){
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,userName);
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

