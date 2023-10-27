package com.dogsole.developersite.jp_apply.service;

import com.dogsole.developersite.common.dto.res.LovResDTO;
import com.dogsole.developersite.common.exception.BusinessException;
import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import com.dogsole.developersite.jobPost.entity.JobPostTempEntity;
import com.dogsole.developersite.jp_apply.dto.JpApplyResDTO;
import com.dogsole.developersite.jp_apply.entity.JpApplyEntity;
import com.dogsole.developersite.jp_apply.repository.JpApplyRepository;
import com.dogsole.developersite.userResume.entity.UserResumeEntity;
import com.dogsole.developersite.vender.entity.UserEntity;
import com.dogsole.developersite.vender.entity.VenderEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JpApplyService {
    private final JpApplyRepository jpApplyRepository;
    private final ModelMapper modelMapper;

    //내 지원목록보기
    public List<JpApplyResDTO> getByUserId(Long id){
        return jpApplyRepository.findByUserEntityId(id).stream()
                .map(jpApplyEntity -> modelMapper.map(jpApplyEntity, JpApplyResDTO.class))
                .collect(Collectors.toList());
    }
    //지원했는지 확인 T/F
    public boolean isJpApplydByUser(Long userId, Long jobPostId) {
        Optional<JpApplyEntity> apply = jpApplyRepository.findByUserEntityIdAndJobPostEntityId(userId, jobPostId);
        return apply.isPresent();
    }

    public boolean addAapplyJp(Long userId, Long venderId, Long jobPostId,Long resumeId) {
       // if (isJpApplydByUser(userId, jobPostId)) {
            JpApplyEntity jpApplyEntity = new JpApplyEntity();

            UserEntity userEntity = new UserEntity();
            userEntity.setId(userId);
            VenderEntity venderEntity = new VenderEntity();
            venderEntity.setId(venderId);
            JobPostEntity jobPostEntity = new JobPostEntity();
            jobPostEntity.setId(jobPostId);
            UserResumeEntity userResumeEntity = new UserResumeEntity();
            userResumeEntity.setId(resumeId);

            jpApplyEntity.setUserEntity(userEntity);
            jpApplyEntity.setVenderEntity(venderEntity);
            jpApplyEntity.setJobPostEntity(jobPostEntity);
            jpApplyEntity.setUserResumeEntity(userResumeEntity);
            jpApplyEntity.setApply_date(LocalDateTime.now());

            jpApplyRepository.save(jpApplyEntity);

            return true; // 저장 성공
//        } else {
//            // 지원 안될 경우
//            return false; // 저장 실패
//        }
    }

    //지원삭제
    public void delApllyJp(Long userId, Long venderId, Long jobPostId) {
        JpApplyEntity jpApplyEntity = jpApplyRepository.findByUserEntity_IdAndVenderEntity_IdAndJobPostEntity_Id(userId, venderId, jobPostId)
                .orElseThrow(() -> new BusinessException("찜을 찾을 수 없음", HttpStatus.NOT_FOUND));
        jpApplyRepository.delete(jpApplyEntity);
    }
}
