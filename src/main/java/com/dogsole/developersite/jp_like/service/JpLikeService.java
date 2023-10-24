package com.dogsole.developersite.jp_like.service;

import com.dogsole.developersite.common.exception.BusinessException;
import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import com.dogsole.developersite.jobPost.entity.JobPostTempEntity;
import com.dogsole.developersite.jp_like.dto.JpLikeResDTO;
import com.dogsole.developersite.jp_like.entity.JpLikeEntity;
import com.dogsole.developersite.jp_like.repository.JpLikeRepository;
import com.dogsole.developersite.vender.entity.UserEntity;
import com.dogsole.developersite.vender.entity.VenderEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class JpLikeService {
    private final JpLikeRepository jpLikeRepository;
    private final ModelMapper modelMapper;

    public JpLikeResDTO getByUserId(Long id) {
        return modelMapper.map(jpLikeRepository.findByUserEntity_id(id), JpLikeResDTO.class);
    }

    public JpLikeEntity addLike(Long userId, Long venderId, Long jobPostId) {
        // 새로운 JpLikeEntity 객체 생성
        JpLikeEntity jpLikeEntity = new JpLikeEntity();

        // 각 객체를 임의의 값으로 설정
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L); // 사용자 ID를 임의로 설정
        VenderEntity venderEntity = new VenderEntity();
        venderEntity.setId(1L); // 판매자 ID를 임의로 설정
        JobPostEntity jobPostEntity = new JobPostEntity();
        jobPostEntity.setId(1L); // 채용 공고 ID를 임의로 설정
        JobPostTempEntity jobPostTempEntity = new JobPostTempEntity();
        jobPostTempEntity.setId(1L); // JobPostTempEntity의 ID를 임의로 설정

        if (userEntity != null && venderEntity != null && jobPostEntity != null && jobPostTempEntity != null) {
            jpLikeEntity.setUserEntity(userEntity);
            jpLikeEntity.setVenderEntity(venderEntity);
            jpLikeEntity.setJobPostEntity(jobPostEntity);
            jpLikeEntity.setJobPostTempEntity(jobPostTempEntity);
            // 저장
            jpLikeEntity = jpLikeRepository.save(jpLikeEntity);
        } else {
            // Entity를 찾을 수 없는 경우 예외 처리
            throw new EntityNotFoundException("Entity를 찾을 수 없습니다.");
        }

        return jpLikeEntity;
    }
//    public boolean isJpLikedByUser(Long userId, Long venderId, Long jobPostId, Long jobPostTempId) {
//        return jpLikeRepository.existsByUserEntity_IdAndVenderEntity_IdAndJobPostEntity_Id(userId, venderId, jobPostId,jobPostTempId);
//    }
//
//    public void toggleJpLike(Long userId, Long venderId, Long jobPostId, Long jobPostTempId) {
//        if (isJpLikedByUser(userId, venderId, jobPostId,jobPostTempId)) {
//            // 이미 찜한 경우, 찜을 삭제
//            unlikeJp(userId, venderId, jobPostId,jobPostTempId);
//        } else {
//            // 아직 찜하지 않은 경우, 찜을 추가
//            likeJp(userId, venderId, jobPostId,jobPostTempId);
//        }
//    }
//
//    private void likeJp(Long userId, Long venderId, Long jobPostId , Long jobPostTempId) {
//        JpLikeEntity jpLikeEntity = new JpLikeEntity();
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setId(userId);
//        VenderEntity venderEntity = new VenderEntity();
//        venderEntity.setId(venderId);
//        JobPostEntity jobPostEntity = new JobPostEntity();
//        jobPostEntity.setId(jobPostId);
//
//        jpLikeEntity.setUserEntity(userEntity);
//        jpLikeEntity.setVenderEntity(venderEntity);
//        jpLikeEntity.setJobPostEntity(jobPostEntity);
//        jpLikeEntity.setJobPostTempEntity(jobPostTempId);
//        jpLikeEntity.setCreatedAt(LocalDateTime.now());
//
//        jpLikeRepository.save(jpLikeEntity);
//    }
//
//    private void unlikeJp(Long userId, Long venderId, Long jobPostId, Long jobPostTempId) {
//        JpLikeEntity jpLikeEntity = jpLikeRepository.findByUserEntity_IdAndVenderEntity_IdAndJobPostEntity_Id(userId, venderId, jobPostId)
//                .orElseThrow(() -> new BusinessException("찜을 찾을 수 없음", HttpStatus.NOT_FOUND));
//        jpLikeRepository.delete(jpLikeEntity);
//    }
}