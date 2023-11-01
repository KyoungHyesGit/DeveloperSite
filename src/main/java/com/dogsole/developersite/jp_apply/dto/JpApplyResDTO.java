package com.dogsole.developersite.jp_apply.dto;

import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.account.entity.user.UserEntity;
import com.dogsole.developersite.jobPost.dto.res.JobPostResDTO;
import com.dogsole.developersite.userResume.entity.UserResumeEntity;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JpApplyResDTO {
    private Long id;
    private VenderResDTO venderEntity;
    private UserEntity userEntity;
    private JobPostResDTO jobPostEntity;
    private UserResumeEntity userResumeEntity;
    private String userState;
    private LocalDateTime apply_date;

}