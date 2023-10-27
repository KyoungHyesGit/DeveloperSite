package com.dogsole.developersite.jp_apply.repository;

import com.dogsole.developersite.jp_apply.entity.JpApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpApplyRepository extends JpaRepository<JpApplyEntity,Long> {

    List<JpApplyEntity> findByUserEntityId(Long userId);
    Optional<JpApplyEntity> findByUserEntityIdAndJobPostEntityId(Long userId, Long jobPostId);
    Optional<JpApplyEntity> findByUserEntity_IdAndVenderEntity_IdAndJobPostEntity_Id(Long userId, Long venderId, Long jobPostId);

}
