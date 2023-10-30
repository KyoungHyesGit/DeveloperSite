package com.dogsole.developersite.jp_like.repository;

import com.dogsole.developersite.jp_like.entity.JpLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpLikeRepository extends JpaRepository<JpLikeEntity, Long> {
    Optional<JpLikeEntity> findByUserEntity_UserId(Long userId);

    Optional<JpLikeEntity> findByUserEntity_UserIdAndJobPostEntity_Id(Long userId, Long jobPostId);
    Optional<JpLikeEntity> findByUserEntity_UserIdAndVenderEntity_VenderIdAndJobPostEntity_IdAndJobPostTempEntity_Id(Long userId, Long venderId, Long jobPostId, Long jobPostTempId);

}