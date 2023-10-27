package com.dogsole.developersite.userResume.service;

import com.dogsole.developersite.userResume.dto.UserResumeReqDTO;
import com.dogsole.developersite.userResume.dto.UserResumeResDTO;
import com.dogsole.developersite.userResume.entity.UserResumeEntity;
import com.dogsole.developersite.userResume.repository.UserResumeRepository;
import com.dogsole.developersite.vender.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserResumeService {
    private final UserResumeRepository userResumeRepository;
    private final ModelMapper modelMapper;

    //user별 이력서 목록
    public List<UserResumeResDTO> getUserResumesByUserId(Long id) {
        List<UserResumeEntity> userResumes = userResumeRepository.findByUserEntityId(id);
        return userResumes.stream()
                .map(userResume -> modelMapper.map(userResume, UserResumeResDTO.class))
                .collect(Collectors.toList());
    }
    public UserResumeEntity saveUserResume(UserResumeReqDTO userResumeReqDTO) {
        UserResumeEntity userResumeEntity = new UserResumeEntity();

        // 폼 데이터를 엔터티로 매핑
        userResumeEntity.setAddr_num(userResumeReqDTO.getAddrNum());
        userResumeEntity.setAddr_basic(userResumeReqDTO.getAddrBasic());
        userResumeEntity.setAddr_detail(userResumeReqDTO.getAddrDetail());
        userResumeEntity.setSkill(userResumeReqDTO.getSkill());
        userResumeEntity.setFinal_education(userResumeReqDTO.getFinalEducation());
        userResumeEntity.setState_resume(userResumeReqDTO.getStateResume());
        userResumeEntity.setState_contact(userResumeReqDTO.getStateContact());
        userResumeEntity.setCertification(userResumeReqDTO.getCertification());
        userResumeEntity.setWork_exp(userResumeReqDTO.getWorkExp());
        userResumeEntity.setOverseas_exp(userResumeReqDTO.getOverseasExp());
        userResumeEntity.setLanguage_skill(userResumeReqDTO.getLanguageSkill());
        userResumeEntity.setPortfolio(userResumeReqDTO.getPortfolio());
        userResumeEntity.setGit_addr(userResumeReqDTO.getGitAddr());
        userResumeEntity.setMilitary_service(userResumeReqDTO.getMilitaryService());
        userResumeEntity.setResume_title_1(userResumeReqDTO.getResumeTitle1());
        userResumeEntity.setResume_content_1(userResumeReqDTO.getResumeContent1());
        userResumeEntity.setPhoto(userResumeReqDTO.getPhoto());

        return userResumeRepository.save(userResumeEntity);
    }


}
