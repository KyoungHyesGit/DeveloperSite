package com.dogsole.developersite.userResume.dto;

import com.dogsole.developersite.vender.entity.UserEntity;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResumeResDTO {
    private Long id;
    private UserEntity userEntity;
    private String addrNum;
    private String addrBasic;
    private String addrDetail;
    private String skill;
    private String finalEducation;
    private String stateResume;
    private String stateContact;
    private String certification;
    private String workExp;
    private String overseasExp;
    private String languageSkill;
    private String portfolio;
    private String gitAddr;
    private String militaryService;
    private String resumeTitle1;
    private String resumeContent1;
    private String photo;
}
