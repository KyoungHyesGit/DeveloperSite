package com.dogsole.developersite.jp_like.service;

import com.dogsole.developersite.account.entity.user.UserEntity;
import com.dogsole.developersite.account.entity.vender.VenderEntity;
import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import com.dogsole.developersite.jp_like.dto.JpLikeResDTO;
import com.dogsole.developersite.jp_like.entity.JpLikeEntity;
import com.dogsole.developersite.jp_like.repository.JpLikeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
    public JpLikeEntity likeJob(Long userId,Long venderId, Long jobPostId ) {
        JpLikeEntity jpLikeEntity = new JpLikeEntity();
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        VenderEntity venderEntity = new VenderEntity();
        venderEntity.setVenderId(venderId);
        JobPostEntity jobPostEntity = new JobPostEntity();
        jobPostEntity.setId(jobPostId);

        jpLikeEntity.setUserEntity(userEntity);
        jpLikeEntity.setJobPostEntity(jobPostEntity);
        jpLikeEntity.setVenderEntity(venderEntity);
        jpLikeEntity.setLike_date(LocalDateTime.now());

        return jpLikeRepository.save(jpLikeEntity);
    }
    //찜 체크
    public boolean isJobLiked(Long userId, Long jobPostId) {
        // 데이터베이스에서 해당 유저와 작업 게시물에 대한 찜 레코드를 조회
        JpLikeEntity like = jpLikeRepository.findByUserEntityUserIdAndJobPostEntityId(userId, jobPostId);

        // 해당 레코드가 존재하면 true를 반환, 그렇지 않으면 false를 반환
        return like != null;
    }
    public void removeLike(Long userId, Long jobPostId) {
        // userId, venderId, jobPostId를 이용하여 해당 찜 데이터를 조회
        JpLikeEntity likeEntity = jpLikeRepository.findByUserEntityUserIdAndJobPostEntityId(userId, jobPostId);

        if (likeEntity != null) {
            // 찜 데이터가 존재하면 삭제
            jpLikeRepository.delete(likeEntity);
        }
    }
}