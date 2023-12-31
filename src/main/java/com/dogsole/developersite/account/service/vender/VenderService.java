package com.dogsole.developersite.account.service.vender;


import com.dogsole.developersite.account.dto.vender.VenderReqDTO;
import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.account.dto.vender.VenderTempReqDTO;
import com.dogsole.developersite.account.dto.vender.VenderTempResDTO;
import com.dogsole.developersite.account.entity.user.UserEntity;
import com.dogsole.developersite.account.entity.vender.VenderEntity;
import com.dogsole.developersite.account.entity.vender.VenderTempEntity;
import com.dogsole.developersite.account.repository.user.UserRepository;
import com.dogsole.developersite.account.repository.vender.VenderRepository;
import com.dogsole.developersite.account.repository.vender.VenderTempRepository;
import com.dogsole.developersite.common.exception.BusinessException;
import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import com.dogsole.developersite.jobPost.entity.JobPostTempEntity;
import com.dogsole.developersite.jwt.entity.TokenEntity;
import com.dogsole.developersite.jwt.provider.JwtTokenProvider;
import com.dogsole.developersite.jwt.repository.TokenRepository;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class VenderService {
    private final VenderTempRepository venderTempRepository;
    private final VenderRepository venderRepository;
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenRepository tokenRepository;

    public VenderTempResDTO updateVenderTemp(Long id, VenderReqDTO venderTemp, MultipartFile file) throws Exception {
        VenderEntity venderEntity = venderRepository.findById(id).orElseThrow(()->new BusinessException("검색 결과 없음", HttpStatus.NOT_FOUND));
//        VenderTempEntity venderTempEntity =
//                modelMapper.map(venderTemp, VenderTempEntity.class);

        //사진 업로드
        String projectPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\img\\vender";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid+"_"+file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);
        venderEntity.setPhoto(fileName);

        venderEntity.setState("수정");
        venderEntity.setTempToReal(venderTemp);

        return modelMapper.map(venderEntity, VenderTempResDTO.class);
    }

    public void allowReq(Long id) {
        // 임시테이블에서 본 테이블로 복사
        VenderTempEntity venderTempEntity = venderTempRepository.findById(id).orElseThrow(()->new BusinessException("검색 결과 없음", HttpStatus.NOT_FOUND));;

        VenderEntity venderEntity = venderRepository.findByTempId(id);

        if(venderEntity==null){
            venderEntity = new VenderEntity();
        }
        venderEntity.setTempToReal(venderTempEntity);

        VenderEntity saveVenderEntity = venderRepository.save(venderEntity);
        UserEntity userEntity = userRepository.findById(venderTempEntity.getUserId()).get();

        if(userEntity!=null && userEntity.getVenderId()!=null){
            userEntity.setVenderId(saveVenderEntity.getVenderId());
        }

        venderTempEntity.setReqState("A"); // A Allow

        venderTempEntity.setVenderId(saveVenderEntity.getVenderId());
    }

    public void refuseReq(Long id) {
        // 임시테이블에서 본 테이블로 복사
        VenderTempEntity venderTempEntity = venderTempRepository.findById(id).orElseThrow(()->new BusinessException("검색 결과 없음", HttpStatus.NOT_FOUND));;

        VenderEntity venderEntity = venderRepository.findByTempId(id);

        UserEntity userEntity = userRepository.findById(venderTempEntity.getUserId()).get();

        if(venderEntity!=null){
            venderRepository.delete(venderEntity);

            if(userEntity!=null && userEntity.getVenderId()!=null){
                userEntity.setVenderId(null);
            }
        }



        venderTempEntity.setReqState("R"); //R Refuse
    }

    //회사회원 전체 조회-------------------------------------------
    @Transactional(readOnly = true)
    public List<VenderResDTO> showVender(){
        List<VenderEntity> venderEntityList=venderRepository.findAll();
        //화면에 뿌려주기 위해
        List<VenderResDTO> userResDTOList = venderEntityList.stream()
                .map(vender -> modelMapper.map(vender, VenderResDTO.class))
                .collect(Collectors.toList());

        return userResDTOList;
    }

    @Transactional(readOnly = true)
    public Page<VenderResDTO> showVender(Pageable pageable){
        Page<VenderEntity> venderEntityList=venderRepository.findAll(pageable);
        //화면에 뿌려주기 위해
        Page<VenderResDTO> userResDTOList = venderEntityList
                .map(vender -> modelMapper.map(vender, VenderResDTO.class));

        return userResDTOList;
    }

    //특정 회사회원 이메일로 검색하기-------------------------------------------------
    public VenderResDTO showVenderByEmail(String vender_email){
        VenderEntity venderEntity = venderRepository.findByVenderEmail(vender_email)
                .orElseThrow();
        VenderResDTO venderResDTO = modelMapper.map(venderEntity, VenderResDTO.class);
        return venderResDTO;
    }

        //특정회사회원 id로 검색
        public VenderResDTO showVenderById(Long id){
        VenderEntity venderEntity = venderRepository.findById(id)
                .orElseThrow();
        VenderResDTO venderResDTO = modelMapper.map(venderEntity, VenderResDTO.class);

        return venderResDTO;
        }

    //회사회원 회원가입-------------------------------------------------------------
    public Boolean venderSignup(VenderReqDTO venderReqDTO){
        //저장소(db)에 동일한 email이 있으면 false처리로 가입실패
        if((venderRepository.existsByVenderEmail(venderReqDTO.getVenderEmail()))){
            return false;
        }

        //스프링 시큐리티로 비밀번호 암호화처리
        String encodePasswd = passwordEncoder.encode(venderReqDTO.getVenderPasswd());
        venderReqDTO.setVenderPasswd(encodePasswd);

        //중복아닐시
        //reqDTO(요청)객체 Entity형으로 전환
        VenderEntity venderEntity = modelMapper.map(venderReqDTO, VenderEntity.class);

        //DB에 등록(가입처리)
        venderRepository.save(venderEntity);

        //가입성공
        return true;
    }
    //회사회원 로그인처리------------------------------------------------------------------
    public VenderResDTO venderLogin(VenderReqDTO venderReqDTO){
        VenderEntity venderEntity = null;
        try{
            venderEntity = venderRepository.findByVenderEmail(venderReqDTO.getVenderEmail()).get();
        }catch (Exception e){

        }
        if (venderEntity!=null){

            if(passwordEncoder.matches(venderReqDTO.getVenderPasswd(),venderEntity.getVenderPasswd())){
                //기업 회원 로그인 성공
                //JWT 토큰 발급 처리-------------------------------------------------------
                //로그인 한 회사회원의 id값 얻기
                String venderId = venderEntity.getVenderId()+"";

                String token = jwtTokenProvider.createToken(venderId); //토큰 생성

                Claims claims = jwtTokenProvider.parseJwtToken("Bearer "+token); //해당 토큰의 claims 객체 생성

                Date tokenIss = claims.getIssuedAt(); //클레임객체에서 토큰 발행일 뜯기
                Date tokenExp = claims.getExpiration(); //클레임객체에서 토큰 만료일 뜯기

                System.out.print("////토큰 발행일자//////"+tokenIss); //토큰발행일 테스트OK
                System.out.print("///토큰////////"+token); //토큰 값 발행 테스트 OK
                System.out.print("///유저아이디/"+venderId+"\n");//회사유저 ID 값 테스트 OK

                //위의 정보로 토큰 엔티티 생성
                TokenEntity tokenEntity = new TokenEntity(token, tokenIss, tokenExp);
                //db에 토큰 엔티티 저장
                tokenRepository.save(tokenEntity);

                return modelMapper.map(venderEntity,VenderResDTO.class);
            }
        }
        return null;
    }
    //회사 회원 탈퇴--------------------------------------------------------------------------
    public void venderLeave(String vender_email){
        //저장소에서 탈퇴하는 회사회원의 객체를 entity형태로 꺼냄
        VenderEntity venderEntity = venderRepository.findByVenderEmail(vender_email)
                .orElseThrow();

        venderEntity.setState("d");
    }


    //회사회원 정보 수정---------------------------------------------
    public VenderReqDTO venderUpdate(Long id, VenderReqDTO venderReqDTO){
        VenderEntity existsVender = venderRepository.findById(id)
                .orElseThrow();

        String encodePasswd = passwordEncoder.encode(venderReqDTO.getVenderPasswd());
        //회사 비번 변경
        existsVender.setVenderPasswd(encodePasswd);
        //회사 이름 변경
        existsVender.setVenderName(venderReqDTO.getVenderName());
        //회사 정보 변경일
        existsVender.setUpdateDt(LocalDateTime.now());
        //회사 사진 변경(추후 커스텀 필요!!!!!!!!!!!!)-------------------------------------------
        existsVender.setPhoto(venderReqDTO.getPhoto());

        return modelMapper.map(existsVender, VenderReqDTO.class);
    }
}