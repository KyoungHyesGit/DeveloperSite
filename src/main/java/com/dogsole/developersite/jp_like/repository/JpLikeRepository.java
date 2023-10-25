package com.dogsole.developersite.jp_like.repository;

import com.dogsole.developersite.jp_like.entity.JpLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpLikeRepository extends JpaRepository<JpLikeEntity, Long> {
    Optional<JpLikeEntity> findByUserEntityId(Long userId);

    Optional<JpLikeEntity> findByUserEntityIdAndJobPostEntityId(Long userId, Long jobPostId);
    Optional<JpLikeEntity> findByUserEntity_IdAndVenderEntity_IdAndJobPostEntity_IdAndJobPostTempEntity_Id(Long userId, Long venderId, Long jobPostId, Long jobPostTempId);

}