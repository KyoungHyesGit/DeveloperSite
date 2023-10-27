package com.dogsole.developersite.userResume.repository;

import com.dogsole.developersite.userResume.entity.UserResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserResumeRepository extends JpaRepository<UserResumeEntity,Long> {
    // 특정 user_id에 대한 resume 목록 가져오기
    List<UserResumeEntity> findByUserEntityId(Long userId);
}
