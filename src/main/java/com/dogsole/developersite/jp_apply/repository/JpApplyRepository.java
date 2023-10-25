package com.dogsole.developersite.jp_apply.repository;

import com.dogsole.developersite.jp_apply.entity.JpApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpApplyRepository extends JpaRepository<JpApplyEntity,Long> {
    Optional<JpApplyEntity> findByUserEntityId(Long userId);
    Optional<JpApplyEntity> findByUserEntityIdAndJobPostEntityId(Long userId, Long jobPostId);
    Optional<JpApplyEntity> findByUserEntity_IdAndVenderEntity_IdAndJobPostEntity_IdAndJobPostTempEntity_Id(Long userId, Long venderId, Long jobPostId, Long jobPostTempId);

}
