package com.dogsole.developersite.jobPost.service;

import com.dogsole.developersite.jobPost.dto.res.JobPostResDTO;
import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import com.dogsole.developersite.jobPost.repository.JobPostRepository;
import com.dogsole.developersite.vender.dto.res.VenderResDTO;
import com.dogsole.developersite.vender.entity.VenderEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;
    private  final ModelMapper modelMapper;

    // 전체공고목록
    public Page<JobPostResDTO> getAllPost(Pageable pageable) {
        Page<JobPostEntity> jobPostEntities = jobPostRepository.findAll(pageable);
        return jobPostEntities.map(jobPostEntity -> {
            JobPostResDTO jobPostResDTO = modelMapper.map(jobPostEntity, JobPostResDTO.class);

            // VenderResDTO를 설정
            VenderEntity venderEntity = jobPostEntity.getVenderEntity();
            VenderResDTO venderResDTO = modelMapper.map(venderEntity, VenderResDTO.class);

            jobPostResDTO.setVenderResDTO(venderResDTO);

            return jobPostResDTO;
        });
    }

    // 상세페이지


}
