package com.dogsole.developersite.jobPost.service;

import com.dogsole.developersite.common.exception.BusinessException;
import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import com.dogsole.developersite.jobPost.entity.JobPostTempEntity;
import com.dogsole.developersite.jobPost.repository.JobPostRepository;
import com.dogsole.developersite.jobPost.repository.JobPostTempRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostTempRepository jobPostTempRepository;
    private final JobPostRepository jobPostRepository;
    private final ModelMapper modelMapper;

    public void allowReq(Long id) {
        // 임시테이블에서 본 테이블로 복사
        JobPostTempEntity jobPostTempEntity = jobPostTempRepository.findById(id).orElseThrow(()->new BusinessException("검색 결과 없음", HttpStatus.NOT_FOUND));;

        JobPostEntity jobPostEntity = new JobPostEntity();
        jobPostEntity.setTempToReal(jobPostTempEntity);
        jobPostRepository.save(jobPostEntity);

        jobPostTempEntity.setReqState("A"); // A Allow
    }
}
