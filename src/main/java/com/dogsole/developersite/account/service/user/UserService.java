package com.dogsole.developersite.account.service.user;

import com.dogsole.developersite.account.dto.user.UserReqDTO;
import com.dogsole.developersite.account.dto.user.UserResDTO;
import com.dogsole.developersite.account.entity.user.UserEntity;
import com.dogsole.developersite.account.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    //비즈니스로직 작성 차례

    //유저 전체 조회------------------------------------------------------------------
    @Transactional(readOnly = true)
    public List<UserResDTO> showUser(){
        List<UserEntity> userList = userRepository.findAll();
        List<UserResDTO> userResDTOList = userList.stream()
                .map(user -> modelMapper.map(user, UserResDTO.class))
                .collect(Collectors.toList());

        return userResDTOList;
    }

    //특정 유저 이메일로 검색하기------------------------------------------------------
    public UserResDTO showUserByEmail(String user_email){
        UserEntity userEntity = userRepository.findByUserEmail(user_email)
                .orElseThrow();
        UserResDTO userResDTO =modelMapper.map(userEntity, UserResDTO.class);
        return userResDTO;
    }

    //회원 가입-------------------------------------------------------------------
    //일반(구직자) 유저 가입처리 (반환형은 트루 펄스)
    public Boolean userSignup(UserReqDTO userReqDTO){
       //저장소(db)에 동일한 email이 있다면 가입 실패 시킴
        if((userRepository.existsByUserEmail(userReqDTO.getUserEmail()))){
            return false;
        }
        //해당 이메일이 중복되지 않는다면
        //reqDTO(요청)객체를 Entity형으로 전환
        UserEntity userEntity = modelMapper.map(userReqDTO, UserEntity.class);

        //db에 등록(가입).
        userRepository.save(userEntity);

        //가입성공
        return true;
    }
    //로그인 처리 --------------------------------------------------------------------
    public boolean userLogin(UserReqDTO userReqDTO){
        if((userRepository.existsByUserEmail(userReqDTO.getUserEmail()))
                &&userRepository.existsByPasswd(userReqDTO.getPasswd())){
            //로그인 성공 alert 띄우기
            return true;
        }
        return false;
    }
    //회원 탈퇴------------------------------------------------------------------------------------
    public void userLeave(String user_email) {
        //저장소에서 탈퇴하는 유저의 객체를 entity형으로 꺼냄
        UserEntity userEntity = userRepository.findByUserEmail(user_email)
                .orElseThrow();

        userEntity.setState("d");
    }
    //회원탈퇴 테스트
    public void userLeaveTest(Long user_id){
        UserEntity userEntity = userRepository.findByUserId(user_id)
                .orElseThrow();
        userEntity.setState("d");
    }

    //회원 정보 수정 ------------------------------------------------------------------------------
    public UserReqDTO userUpdate(String user_email, UserReqDTO userReqDTO){
        //저장소에서 로그인한 유저의 이메일과 동일한 값을 가진 객체를 가져올 것
        UserEntity existsUser = userRepository.findByUserEmail(user_email)
                        .orElseThrow();
        //비번 변경
        existsUser.setPasswd(userReqDTO.getPasswd());
        //이름 변경
        existsUser.setUserName(userReqDTO.getUserName());
        //폰 변경
        existsUser.setPhone(userReqDTO.getPhone());
        //증명사진 변경(추후 커스텀필요!!!!!!!!!)--------------------------------
        existsUser.setPhone(userReqDTO.getPhoto());

        return modelMapper.map(existsUser, UserReqDTO.class);
    }
    //회원 수정 테스트--------
    public UserReqDTO userUpdateTest(Long user_id, UserReqDTO userReqDTO){
        //비번변경
        UserEntity existsUser = userRepository.findByUserId(user_id)
                .orElseThrow();

        //비번 변경
        existsUser.setPasswd(userReqDTO.getPasswd());
        //이름 변경
        existsUser.setUserName(userReqDTO.getUserName());
        return modelMapper.map(existsUser, UserReqDTO.class);
    }
}
