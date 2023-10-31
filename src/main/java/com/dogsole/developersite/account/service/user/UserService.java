package com.dogsole.developersite.account.service.user;

import com.dogsole.developersite.account.dto.user.UserReqDTO;
import com.dogsole.developersite.account.dto.user.UserResDTO;
import com.dogsole.developersite.account.entity.user.UserEntity;
import com.dogsole.developersite.account.repository.user.UserRepository;
import com.dogsole.developersite.jwt.dto.TokenReqDTO;
import com.dogsole.developersite.jwt.entity.TokenEntity;
import com.dogsole.developersite.jwt.provider.JwtTokenProvider;
import com.dogsole.developersite.jwt.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper; //객체 변환
    private final JwtTokenProvider jwtTokenProvider; //토큰 공급
    private final TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;    //비즈니스로직 작성 차례

    //유저 전체 조회------------------------------------------------------------------
    @Transactional(readOnly = true)
    public List<UserResDTO> showUser(){
        List<UserEntity> userList = userRepository.findAll();
        List<UserResDTO> userResDTOList = userList.stream()
                .map(user -> modelMapper.map(user, UserResDTO.class))
                .collect(Collectors.toList());

        return userResDTOList;
    }

    @Transactional(readOnly = true)
    public Page<UserResDTO> showUser(Pageable pageable){
        return userRepository.findAll(pageable)
                .map(user -> modelMapper.map(user, UserResDTO.class));
    }

    //특정 유저 이메일로 검색하기------------------------------------------------------
    public UserResDTO showUserByEmail(String user_email){
        UserEntity userEntity = userRepository.findByUserEmail(user_email)
                .orElseThrow();
        UserResDTO userResDTO =modelMapper.map(userEntity, UserResDTO.class);
        return userResDTO;
    }
    //특정 유저 아이디로 검색하기-----------------------------------------------------
    public UserResDTO showUserById(Long id){
        UserEntity userEntity = userRepository.findByUserId(id)
                .orElseThrow();
        UserResDTO userResDTO = modelMapper.map(userEntity, UserResDTO.class);

        return userResDTO;
    }

    //회원 가입-------------------------------------------------------------------
    //일반(구직자) 유저 가입처리 (반환형은 트루 펄스)
    public Boolean userSignup(UserReqDTO userReqDTO){
       //저장소(db)에 동일한 email이 있다면 가입 실패 시킴
        if((userRepository.existsByUserEmail(userReqDTO.getUserEmail()))){
            return false;
        }
        //스프링 시큐리티로 비밀번호 암호화처리
        String encodePasswd = passwordEncoder.encode(userReqDTO.getPasswd());
        userReqDTO.setPasswd(encodePasswd);

        UserEntity userEntity = modelMapper.map(userReqDTO, UserEntity.class);

        //db에 등록(가입).
        userRepository.save(userEntity);

        //가입성공
        return true;
    }
    //로그인 처리 --------------------------------------------------------------------
    public boolean userLogin(UserReqDTO userReqDTO){
        if((userRepository.existsByUserEmail(userReqDTO.getUserEmail()))
                &&(userRepository.existsByPasswd(userReqDTO.getPasswd()))){
            //유저 상태값이 d(삭제상태)인지 확인
            UserEntity userEntityState = userRepository.findByUserEmail(userReqDTO.getUserEmail()).orElseThrow();
            System.out.println("로그인 진입시 값"+userEntityState.getState());

            if(userEntityState.getState().equals("d")){
                System.out.println("로그인실패시값"+userEntityState.getState());
                return false;
            }

            //로그인 성공 alert 띄우기
            //로그인 성공 후 토큰 발급 처리.--------------------------------------------
            //로그인 한 유저 객체의 id 값 얻기
            UserEntity userEntity = userRepository.findByUserEmail(userReqDTO.getUserEmail()).orElseThrow();
            String userId = userEntity.getUserId()+"";

            String token = jwtTokenProvider.createToken(userId); //토큰 생성

            Claims claims = jwtTokenProvider.parseJwtToken("Bearer "+ token); //해당 토큰의 claims 객체 생성


            Date tokenIss = claims.getIssuedAt(); //클레임객체에서 토큰 발행일 뜯기
            Date tokenExp = claims.getExpiration(); //클레임객체에서 토큰 만료일 뜯기

            System.out.print("////토큰 발행일자//////"+tokenIss); //토큰발행일 테스트OK
            System.out.print("///토큰////////"+token); //토큰 값 발행 테스트 OK
            System.out.print("///유저아이디/"+userId+"\n");//유저 ID 값 테스트 OK

            //토큰 테이블에는 토큰과 토큰발행일, 토큰만료일이 담김
            TokenEntity tokenEntity = new TokenEntity(token, tokenIss, tokenExp);
            //DB에 토큰저장하기
            tokenRepository.save(tokenEntity);
            //토큰저장이안대 씨발 10/27/11시38분
            System.out.println("로그인성공시값"+userEntityState.getState());
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

    public void userBlock(Long id) {
        //저장소에서 탈퇴하는 유저의 객체를 entity형으로 꺼냄
        UserEntity userEntity = userRepository.findByUserId(id)
                .orElseThrow();

        userEntity.setState("B");
    }

    //회원탈퇴 테스트 이거로 진행중!!!!!!!!!!!!!!!!!!!!!!!

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
    //회원 수정 테스트--------------------(이거로 사용중!!!!!!!!!!!!!!!!!!!!!!!)
    public void userUpdateTest(Long user_id, UserReqDTO userReqDTO){
        UserEntity existsUser = userRepository.findByUserId(user_id)
                .orElseThrow();
        String encodePasswd = passwordEncoder.encode(userReqDTO.getPasswd());
        //비번 변경
        existsUser.setPasswd(encodePasswd);
        //이름 변경
        existsUser.setUserName(userReqDTO.getUserName());
        //폰 변경
        existsUser.setPhone(userReqDTO.getPhone());
    }
}
