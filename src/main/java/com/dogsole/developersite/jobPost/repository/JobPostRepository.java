package com.dogsole.developersite.jobPost.repository;

import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostRepository extends JpaRepository<JobPostEntity, Long> {
    // 전체공고목록
    Page<JobPostEntity> findAll(Pageable pageable);

    JobPostEntity findByTempId(Long tempId);

}
