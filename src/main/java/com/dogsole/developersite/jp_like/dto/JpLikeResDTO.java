package com.dogsole.developersite.jp_like.dto;

import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.account.entity.user.UserEntity;
import com.dogsole.developersite.jobPost.dto.res.JobPostResDTO;

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
    private VenderResDTO venderEntity;
    private UserEntity userEntity;
    private JobPostResDTO jobPostEntity;
    private LocalDateTime like_date;

}