package com.dogsole.developersite.jp_like.repository;

import com.dogsole.developersite.jpLike.entity.JpLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpLikeRepository extends JpaRepository<JpLikeEntity, Long> {
    Optional<JpLikeEntity> findByUserId(int id);
}