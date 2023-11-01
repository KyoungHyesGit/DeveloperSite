package com.dogsole.developersite.jp_apply.service;

import com.dogsole.developersite.account.entity.user.UserEntity;
import com.dogsole.developersite.account.entity.vender.VenderEntity;
import com.dogsole.developersite.common.exception.BusinessException;
import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import com.dogsole.developersite.jp_apply.dto.JpApplyResDTO;
import com.dogsole.developersite.jp_apply.entity.JpApplyEntity;
import com.dogsole.developersite.jp_apply.repository.JpApplyRepository;
import com.dogsole.developersite.userResume.dto.UserResumeResDTO;
import com.dogsole.developersite.userResume.entity.UserResumeEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return jpApplyRepository.findByUserEntityUserId(id).stream()
                .map(jpApplyEntity -> modelMapper.map(jpApplyEntity, JpApplyResDTO.class))
                .collect(Collectors.toList());
    }

    //지원했는지 확인 T/F
    public boolean isJpApplydByUser(Long userId, Long jobPostId) {
        Optional<JpApplyEntity> apply = jpApplyRepository.findByUserEntityUserIdAndJobPostEntityId(userId, jobPostId);
        return apply.isPresent();
    }


    public boolean addAapplyJp(Long userId, Long venderId, Long jobPostId,Long resumeId) {
        JpApplyEntity jpApplyEntity = new JpApplyEntity();

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        VenderEntity venderEntity = new VenderEntity();
        venderEntity.setVenderId(venderId);
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
    }

    //지원삭제
    public void delApllyJp( Long jpApplyId) {
        JpApplyEntity jpApplyEntity = jpApplyRepository.findById(jpApplyId)
                .orElseThrow(() -> new BusinessException("지원글을 찾을 수 없음", HttpStatus.NOT_FOUND));
        jpApplyRepository.delete(jpApplyEntity);
    }
    public Page<JpApplyResDTO> getPostResumeList(Long postId, Pageable pageable){
        Page<JpApplyEntity> postResumeList = jpApplyRepository.findByJobPostEntityId(postId, pageable);
        return postResumeList.map(jpApplyEntity -> modelMapper.map(jpApplyEntity, JpApplyResDTO.class));
    }

    public String changeUserState(Long jpApplyId, String userState) {
        JpApplyEntity jpApplyEntity = jpApplyRepository.findById(jpApplyId)
                .orElseThrow(() -> new BusinessException("지원글을 찾을 수 없음", HttpStatus.NOT_FOUND));

        jpApplyEntity.setUserState(userState);
        return userState;
    }
    //지원확인
    public boolean isAlreadyApplied(Long userId, Long jobPostId) {
        return jpApplyRepository.existsByUserEntityUserIdAndJobPostEntityId(userId, jobPostId);
    }
}