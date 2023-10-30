package com.dogsole.developersite.jobPost.service;

import com.dogsole.developersite.account.entity.vender.VenderEntity;
import com.dogsole.developersite.common.exception.BusinessException;
import com.dogsole.developersite.jobPost.dto.req.JobPostTempReqDTO;
import com.dogsole.developersite.jobPost.dto.req.JobPostTempReqFormDTO;
import com.dogsole.developersite.jobPost.dto.res.JobPostTempResDTO;
import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import com.dogsole.developersite.jobPost.entity.JobPostTempEntity;
import com.dogsole.developersite.jobPost.repository.JobPostTempRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class JobPostTempService {
    private final JobPostTempRepository jobPostTempRepository;
    private final ModelMapper modelMapper;
    public Page<JobPostTempResDTO> getAllPosts(Pageable pageable) {
        return jobPostTempRepository.findAll(pageable).map(jobPostTempEntity -> modelMapper.map(jobPostTempEntity, JobPostTempResDTO.class));
    }

    public JobPostTempResDTO createJobTempPost(JobPostTempReqDTO jobPostTempReqDTO) {
        JobPostTempEntity jobPostTempEntity =
                modelMapper.map(jobPostTempReqDTO, JobPostTempEntity.class);
        jobPostTempEntity.setVenderEntity(modelMapper.map(jobPostTempReqDTO.getVenderReqDTO(), VenderEntity.class));
        jobPostTempRepository.save(jobPostTempEntity);
        
        return modelMapper.map(jobPostTempEntity, JobPostTempResDTO.class);
    }

    public JobPostTempResDTO getJobTempPost(Long id) {
        return modelMapper.map(jobPostTempRepository.findById(id).orElseThrow(()->new BusinessException("검색 결과 없음", HttpStatus.NOT_FOUND)), JobPostTempResDTO.class);
    }

    public JobPostTempResDTO updateJobPostTemp(Long id, JobPostTempReqFormDTO tempReqDTO){
        JobPostTempEntity jobPostTempEntity = jobPostTempRepository.findById(id).orElseThrow(()->new BusinessException("검색 결과 없음", HttpStatus.NOT_FOUND));;
        jobPostTempEntity.setDTOsValToEntity(tempReqDTO);

        System.out.println(jobPostTempEntity);

        return modelMapper.map(jobPostTempEntity, JobPostTempResDTO.class);
    }
}
