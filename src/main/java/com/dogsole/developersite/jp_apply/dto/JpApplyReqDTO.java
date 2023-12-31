package com.dogsole.developersite.jp_apply.dto;

import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import com.dogsole.developersite.userResume.entity.UserResumeEntity;
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
    private UserResumeEntity userResumeEntity;
    private LocalDateTime like_date = LocalDateTime.now();
    private String userState;

}