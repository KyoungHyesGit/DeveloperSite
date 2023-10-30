package com.dogsole.developersite.jp_apply.repository;

import com.dogsole.developersite.jp_apply.entity.JpApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpApplyRepository extends JpaRepository<JpApplyEntity,Long> {

    List<JpApplyEntity> findByUserEntity_UserId(Long userId);
    Optional<JpApplyEntity> findByUserEntity_UserIdAndJobPostEntity_Id(Long userId, Long jobPostId);
    Optional<JpApplyEntity> findByUserEntity_UserIdAndVenderEntity_VenderIdAndJobPostEntity_Id(Long userId, Long venderId, Long jobPostId);

}
