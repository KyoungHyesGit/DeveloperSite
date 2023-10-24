package com.dogsole.developersite.jp_like.repository;

import com.dogsole.developersite.jp_like.entity.JpLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpLikeRepository extends JpaRepository<JpLikeEntity, Long> {
    Optional<JpLikeEntity> findByUserEntity_id(Long userEntity_id);
//    Optional<JpLikeEntity> findByUserEntity_user_id(@Param("user_id") Long userId);
}