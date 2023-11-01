package com.dogsole.developersite.jp_like.dto;

import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.account.entity.user.UserEntity;
import com.dogsole.developersite.jobPost.dto.res.JobPostTempResDTO;
import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JpLikeReqDTO {
    private Long id;

    private VenderResDTO venderResDTO;
    private UserEntity userEntity;
    private JobPostEntity jobPostEntity;
    private LocalDateTime like_date = LocalDateTime.now();

}