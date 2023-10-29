package com.dogsole.developersite.jobPost.repository;

import com.dogsole.developersite.jobPost.entity.JobPostTempEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostTempRepository extends JpaRepository<JobPostTempEntity, Long> {
    // 페이징 및 정렬
    Page<JobPostTempEntity> findByVenderEntity_VenderId(Long venderId, Pageable pageable);
    Page<JobPostTempEntity> findAll(Pageable pageable);
}
