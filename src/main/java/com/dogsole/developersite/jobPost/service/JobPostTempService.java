package com.dogsole.developersite.jobPost.service;

import com.dogsole.developersite.jobPost.dto.res.JobPostTempResDTO;
import com.dogsole.developersite.jobPost.entity.JobPostTempEntity;
import com.dogsole.developersite.jobPost.repository.JobPostTempRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobPostTempService {
    private final JobPostTempRepository jobPostTempRepository;
    private final ModelMapper modelMapper;
    public Page<JobPostTempResDTO> getAllPosts(Pageable pageable) {
        return jobPostTempRepository.findAll(pageable).map(jobPostTempEntity -> modelMapper.map(jobPostTempEntity, JobPostTempResDTO.class));
    }



}
