package com.dogsole.developersite.jobPost.repository;

import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostRepository extends JpaRepository<JobPostEntity, Long> {



}
