package com.dogsole.developersite.jwt.controller;

import com.dogsole.developersite.jwt.dto.TokenDataResponse;
import com.dogsole.developersite.jwt.dto.TokenResponse;
import com.dogsole.developersite.jwt.dto.TokenResponseNoData;
import com.dogsole.developersite.jwt.provider.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {

  private final JwtTokenProvider jwtTokenProvider;

  //토큰 생성 컨트롤러---------------------------------------
    @GetMapping(value = "/tokenCreate/{userId}") //토큰 요청경로.
    public TokenResponse createToken(@PathVariable("userId") String userId) throws Exception {

        String token = jwtTokenProvider.createToken(userId); // 토큰 생성

        Claims claims = jwtTokenProvider.parseJwtToken("Bearer "+ token); // 토큰 검증

        //클레임객체에서 토큰의 값들을 분해해서 응답토큰에 저장함
        TokenDataResponse tokenDataResponse =
                new TokenDataResponse(token, claims.getSubject(),
                        claims.getIssuedAt().toString(),
                        claims.getExpiration().toString());

        TokenResponse tokenResponse = new TokenResponse("200", "OK", tokenDataResponse);

        return tokenResponse;
    }

    //토큰 인증 컨트롤러(검증성공)-------------------------------------------------------------
    @GetMapping(value = "/checkToken")
    public TokenResponseNoData checkToken(@RequestHeader(value = "Authorization") String token) throws Exception {
        Claims claims = jwtTokenProvider.parseJwtToken(token);

        TokenResponseNoData tokenResponseNoData = new TokenResponseNoData("200", "success");
        return tokenResponseNoData;
    }

}
