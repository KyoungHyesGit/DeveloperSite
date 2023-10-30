package com.dogsole.developersite.jobPost.service;

import com.dogsole.developersite.account.entity.vender.VenderEntity;
import com.dogsole.developersite.common.exception.BusinessException;
import com.dogsole.developersite.jobPost.dto.req.JobPostTempReqDTO;
import com.dogsole.developersite.jobPost.dto.req.JobPostTempReqFormDTO;
import com.dogsole.developersite.jobPost.dto.res.JobPostTempResDTO;
import com.dogsole.developersite.jobPost.entity.JobPostTempEntity;
import com.dogsole.developersite.jobPost.repository.JobPostTempRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobPostTempService {
    private final JobPostTempRepository jobPostTempRepository;
    private final ModelMapper modelMapper;

    public Page<JobPostTempResDTO> getVendersPosts(Long venderId, Pageable pageable) {
        return jobPostTempRepository.findByVenderEntity_VenderId(venderId,pageable).map(jobPostTempEntity -> modelMapper.map(jobPostTempEntity, JobPostTempResDTO.class));
    }
    public Page<JobPostTempResDTO> getAllPosts(Pageable pageable) {
        return jobPostTempRepository.findAll(pageable).map(jobPostTempEntity -> modelMapper.map(jobPostTempEntity, JobPostTempResDTO.class));
    }

    public JobPostTempResDTO createJobTempPost(JobPostTempReqDTO jobPostTempReqDTO) {
        JobPostTempEntity jobPostTempEntity =
                modelMapper.map(jobPostTempReqDTO, JobPostTempEntity.class);
        jobPostTempEntity.setVenderEntity(modelMapper.map(jobPostTempReqDTO.getVenderReqDTO(), VenderEntity.class));
        jobPostTempEntity.setReq(String.join(",", jobPostTempReqDTO.getReq()));
        jobPostTempEntity.setWork(String.join(",", jobPostTempReqDTO.getWork()));

        jobPostTempRepository.save(jobPostTempEntity);
        
        return modelMapper.map(jobPostTempEntity, JobPostTempResDTO.class);
    }

    public JobPostTempResDTO getJobTempPost(Long id) {
        JobPostTempEntity jobPostTempEntity =  jobPostTempRepository.findById(id).orElseThrow(()->new BusinessException("검색 결과 없음", HttpStatus.NOT_FOUND));
        JobPostTempResDTO jobPostTempResDTO = modelMapper.map(jobPostTempEntity, JobPostTempResDTO.class);
        jobPostTempResDTO.setReq(Arrays.stream(jobPostTempEntity.getReq().split(",")).toList());
        jobPostTempResDTO.setWork(Arrays.stream(jobPostTempEntity.getWork().split(",")).toList());
        return jobPostTempResDTO;
    }

    public JobPostTempResDTO updateJobPostTemp(Long id, JobPostTempReqFormDTO tempReqDTO){
        JobPostTempEntity jobPostTempEntity = jobPostTempRepository.findById(id).orElseThrow(()->new BusinessException("검색 결과 없음", HttpStatus.NOT_FOUND));;
        jobPostTempEntity.setDTOsValToEntity(tempReqDTO);
        jobPostTempEntity.setReq(String.join(",", tempReqDTO.getReq()));
        jobPostTempEntity.setWork(String.join(",", tempReqDTO.getWork()));

        System.out.println(jobPostTempEntity);

        return modelMapper.map(jobPostTempEntity, JobPostTempResDTO.class);
    }

    public JobPostTempResDTO deleteJobPostTemp(Long id){
        JobPostTempEntity jobPostTempEntity = jobPostTempRepository.findById(id).orElseThrow(()->new BusinessException("검색 결과 없음", HttpStatus.NOT_FOUND));;
        jobPostTempEntity.setState("D"); // D Delete
        jobPostTempEntity.setReqState("R");

        return modelMapper.map(jobPostTempEntity, JobPostTempResDTO.class);
    }
}
