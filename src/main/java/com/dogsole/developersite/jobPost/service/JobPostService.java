package com.dogsole.developersite.jobPost.service;


import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.account.entity.vender.VenderEntity;
import com.dogsole.developersite.common.exception.BusinessException;
import com.dogsole.developersite.jobPost.dto.res.JobPostResDTO;
import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import com.dogsole.developersite.jobPost.entity.JobPostTempEntity;
import com.dogsole.developersite.jobPost.repository.JobPostRepository;
import com.dogsole.developersite.jobPost.repository.JobPostTempRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public JobPostResDTO getJobDetail(Long id) {
        JobPostEntity jobPostEntity = jobPostRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException(id + " JobPost Not Found", HttpStatus.NOT_FOUND));
        VenderEntity venderEntity = jobPostEntity.getVenderEntity();
        JobPostResDTO jobPostResDTO = modelMapper.map(jobPostEntity, JobPostResDTO.class);
        VenderResDTO venderResDTO = modelMapper.map(venderEntity, VenderResDTO.class);
        jobPostResDTO.setVenderResDTO(venderResDTO);

        return jobPostResDTO;
    }


}
