package com.dogsole.developersite.jp_like.service;

import com.dogsole.developersite.jpLike.dto.JpLikeResDTO;
import com.dogsole.developersite.jpLike.repository.JpLikeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpLikeService {
    private final JpLikeRepositorygit jpLikeRepository;
    private final ModelMapper modelMapper;

    public JpLikeResDTO getByUserId(int id){
        return modelMapper.map(jpLikeRepository.findByUserId(id),JpLikeResDTO.class);
    }
//    public String likeInsert(int id) {
//        Users user = userRepository.findByUserId(id);
//        job_post_id = jobPostRepository.findById();
//        like.setUser(user);
//        like.setProduct();
//        Like saveLike = jpLikeRepository.save(like);
//        return "";
//
//    }
}