package com.dogsole.developersite.jp_like.dto;

import com.dogsole.developersite.jobPost.dto.res.JobPostResDTO;
import com.dogsole.developersite.jobPost.dto.res.JobPostTempResDTO;
import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import com.dogsole.developersite.vender.dto.res.VenderResDTO;
import com.dogsole.developersite.vender.entity.UserEntity;
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
    private JobPostTempResDTO jobPostTempEntity;
    private LocalDateTime like_date;

}