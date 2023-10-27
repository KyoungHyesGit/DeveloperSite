package com.dogsole.developersite.jwt.repository;

import com.dogsole.developersite.jwt.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository <TokenEntity,Long> {
    //토큰으로 로그인(토큰이 있다면 true, 없으면 false)
    public boolean existsByToken(String token);


}
