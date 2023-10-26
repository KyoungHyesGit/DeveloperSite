package com.dogsole.developersite.common.repository;

import com.dogsole.developersite.common.entity.LovEntity;
import com.dogsole.developersite.jobPost.entity.JobPostTempEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LovRepository extends JpaRepository<LovEntity, Long> {
    List<LovEntity> findByKind(String kind);
}
