package com.dogsole.developersite.jp_apply.dto;

import com.dogsole.developersite.jobPost.dto.res.JobPostTempResDTO;
import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import com.dogsole.developersite.vender.dto.res.VenderResDTO;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JpApplyReqDTO {
    private Long id;

    private VenderResDTO venderResDTO;
//    private UserEntity userEntity;
    private JobPostEntity jobPostEntity;
    private JobPostTempResDTO jobPostTempReqDTO;
    private LocalDateTime like_date = LocalDateTime.now();

}