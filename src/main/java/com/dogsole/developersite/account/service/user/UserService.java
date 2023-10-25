package com.dogsole.developersite.account.service.user;

import com.dogsole.developersite.account.dto.user.UserReqDTO;
import com.dogsole.developersite.account.entity.user.UserEntity;
import com.dogsole.developersite.account.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    //비즈니스로직 작성 차례
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
    //회원 탈퇴------------------------------------------------------------------------------------
    public void userLeave(String user_email) {
        //저장소에서 탈퇴하는 유저의 객체를 entity형으로 꺼냄
        UserEntity userEntity = userRepository.findByUserEmail(user_email)
                .orElseThrow();
        //저장소에서 꺼낸 entity형의 유저객체를 Req형으로 변환
        userEntity.setState("d");
    }//가입화면 구성해서 db값 테스트해보자.

    //회원 수정 ------------------------------------------------------------------------------

    public void userUpdate(String user_email, UserReqDTO userReqDTO){
        //저장소에서 로그인한 유저의 이메일과 동일한 값을 가진 객체를 가져올 것
        userRepository.findByUserEmail(user_email);
    }
}
