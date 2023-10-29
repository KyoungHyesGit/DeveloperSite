package com.dogsole.developersite.jp_like.service;

import com.dogsole.developersite.account.entity.user.UserEntity;
import com.dogsole.developersite.common.exception.BusinessException;
import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import com.dogsole.developersite.jobPost.entity.JobPostTempEntity;
import com.dogsole.developersite.jp_apply.dto.JpApplyResDTO;
import com.dogsole.developersite.jp_like.dto.JpLikeResDTO;
import com.dogsole.developersite.jp_like.entity.JpLikeEntity;
import com.dogsole.developersite.jp_like.repository.JpLikeRepository;
import com.dogsole.developersite.vender.entity.VenderEntity;
import jakarta.persistence.EntityNotFoundException;
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
public class JpLikeService {
    private final JpLikeRepository jpLikeRepository;
    private final ModelMapper modelMapper;
    //내찜리스트
    public List<JpLikeResDTO> getByUserId(Long id){
        return jpLikeRepository.findByUserEntityUserId(id).stream()
                .map(jpLikeEntity -> modelMapper.map(jpLikeEntity, JpLikeResDTO.class))
                .collect(Collectors.toList());
    }
    // 찜하기
    public JpLikeEntity likeJob(Long userId, Long jobPostId ) {
        JpLikeEntity jpLikeEntity = new JpLikeEntity();
        UserEntity userEntity = modelMapper.map(userId, UserEntity.class);
        JobPostEntity jobPostEntity = modelMapper.map(jobPostId, JobPostEntity.class);
        jpLikeEntity.setUserEntity(userEntity);
        jpLikeEntity.setJobPostEntity(jobPostEntity);

        return jpLikeRepository.save(jpLikeEntity);
    }


//    public void unlikeJob(UserEntity user, JobPostEntity jobPost) {
//        JpLikeEntity jpLikeEntity = jpLikeRepository.findByUserEntityAndJobPostEntity(user, jobPost);
//        if (jpLikeEntity != null) {
//            jpLikeRepository.delete(jpLikeEntity);
//        }
//    }
//
//    public boolean isJobLikedByUser(UserEntity user, JobPostEntity jobPost) {
//        return jpLikeRepository.existsByUserEntityAndJobPostEntity(user, jobPost);
//    }
//
//    private void unlikeJp(Long userId, Long venderId, Long jobPostId, Long jobPostTempId) {
//        JpLikeEntity jpLikeEntity = jpLikeRepository.findByUserEntity_IdAndVenderEntity_IdAndJobPostEntity_IdAndJobPostTempEntity_Id(userId, venderId, jobPostId,jobPostTempId)
//                .orElseThrow(() -> new BusinessException("찜을 찾을 수 없음", HttpStatus.NOT_FOUND));
//        jpLikeRepository.delete(jpLikeEntity);
//    }
}