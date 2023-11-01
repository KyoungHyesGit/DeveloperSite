package com.dogsole.developersite.userResume.service;

import com.dogsole.developersite.account.entity.user.UserEntity;
import com.dogsole.developersite.userResume.dto.UserResumeReqDTO;
import com.dogsole.developersite.userResume.dto.UserResumeResDTO;
import com.dogsole.developersite.userResume.entity.UserResumeEntity;
import com.dogsole.developersite.userResume.repository.UserResumeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserResumeService {
    private final UserResumeRepository userResumeRepository;
    private final ModelMapper modelMapper;

    //user별 이력서 목록
    public List<UserResumeResDTO> getUserResumesByUserId(Long id) {
        List<UserResumeEntity> userResumes = userResumeRepository.findByUserEntityUserId(id);
        return userResumes.stream()
                .map(userResume -> modelMapper.map(userResume, UserResumeResDTO.class))
                .collect(Collectors.toList());
    }
    public UserResumeEntity saveUserResume(UserResumeReqDTO userResumeReqDTO, MultipartFile file,Long userId) throws Exception{
        UserResumeEntity userResumeEntity = modelMapper.map(userResumeReqDTO, UserResumeEntity.class);

        //사진 업로드
        String projectPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\resumeImage";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid+"_"+file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);
        userResumeEntity.setPhoto(fileName);
        userResumeEntity.setPhotoUrl("/resumeImage/"+fileName);

        userId=1L;
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userResumeEntity.setUserEntity(userEntity);

        return userResumeRepository.save(userResumeEntity);
    }
    //이력서 상세
    public UserResumeResDTO getUserResumeByid(Long id){
        return modelMapper.map(userResumeRepository.findUserResumeEntityById(id),UserResumeResDTO.class);
    }
    // 이력서 삭제 메서드
    public void deleteResumeById(Long id) {
        // 이력서 ID로 이력서를 찾아냅니다.
        UserResumeEntity resume = userResumeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 이력서를 찾을 수 없습니다: " + id));

        // 이력서를 삭제합니다.
        userResumeRepository.delete(resume);
    }
    public Long getUserIdByResumeId(Long resumeId) {
        // 이력서 ID를 사용하여 user_id를 가져오는 메서드
        UserResumeEntity userResume = userResumeRepository.findById(resumeId).orElse(null);
        if (userResume != null) {
            return userResume.getUserEntity().getUserId();
        } else {
            return null; // 이력서를 찾지 못한 경우 예외 처리
        }
    }



}
