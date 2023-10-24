package com.dogsole.developersite.jp_like.dto;

import com.dogsole.developersite.jobPost.dto.res.JobPostTempResDTO;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JpLikeResDTO {
    private Long id;

    private VenderResDTO venderResDTO;
    private UserEntity userEntity;
    private JobPostEntity jobPostEntity;
    private JobPostTempResDTO jobPostTempReqDTO;
    private LocalDateTime like_date = LocalDateTime.now();

}