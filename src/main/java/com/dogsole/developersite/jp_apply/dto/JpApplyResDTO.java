package com.dogsole.developersite.jp_apply.dto;

import com.dogsole.developersite.jobPost.dto.res.JobPostResDTO;
import com.dogsole.developersite.jobPost.dto.res.JobPostTempResDTO;
import com.dogsole.developersite.userResume.entity.UserResumeEntity;
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
public class JpApplyResDTO {
    private Long id;
    private VenderResDTO venderEntity;
    private UserEntity userEntity;
    private JobPostResDTO jobPostEntity;
    private UserResumeEntity userResumeEntity;
    private LocalDateTime apply_date;

}