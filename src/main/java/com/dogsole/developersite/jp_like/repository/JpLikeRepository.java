package com.dogsole.developersite.jp_like.repository;

import com.dogsole.developersite.jp_like.entity.JpLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpLikeRepository extends JpaRepository<JpLikeEntity, Long> {
    List<JpLikeEntity> findByUserEntityUserId(Long userId);

    JpLikeEntity findByUserEntityUserIdAndJobPostEntityId(Long userId, Long jobPostId);

}