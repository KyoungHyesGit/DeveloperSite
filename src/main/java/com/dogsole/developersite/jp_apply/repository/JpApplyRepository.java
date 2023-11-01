package com.dogsole.developersite.jp_apply.repository;

import com.dogsole.developersite.jp_apply.entity.JpApplyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpApplyRepository extends JpaRepository<JpApplyEntity,Long> {

    List<JpApplyEntity> findByUserEntityUserId(Long userId);
    Optional<JpApplyEntity> findByUserEntityUserIdAndJobPostEntityId(Long userId, Long jobPostId);

    Page<JpApplyEntity> findByJobPostEntityId(Long postId, Pageable pageable );


}