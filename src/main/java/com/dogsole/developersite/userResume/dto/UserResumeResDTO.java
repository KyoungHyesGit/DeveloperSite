package com.dogsole.developersite.userResume.dto;

import com.dogsole.developersite.account.entity.user.UserEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResumeResDTO {
    private Long id;
    private UserEntity userEntity;
    private String addr_num;
    private String addr_basic;
    private String addr_detail;
    private String skill;
    private String final_education;
    private String state_resume;
    private String state_contact;
    private String certification;
    private String work_exp;
    private String overseas_exp;
    private String language_skill;
    private String portfolio;
    private String git_addr;
    private String military_service;
    private String resume_title_1;
    private String resume_content_1;
    private MultipartFile photo;
    private String photoUrl;
}
