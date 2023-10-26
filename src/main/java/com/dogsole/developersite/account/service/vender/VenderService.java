package com.dogsole.developersite.account.service.vender;

import com.dogsole.developersite.account.dto.user.UserReqDTO;
import com.dogsole.developersite.account.dto.vender.VenderReqDTO;
import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.account.entity.vender.VenderEntity;
import com.dogsole.developersite.account.repository.vender.VenderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class VenderService {
    private final VenderRepository venderRepository;
    private final ModelMapper modelMapper;

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

    //특정 회사회원 이메일로 검색하기-------------------------------------------------
        public VenderResDTO showVenderByEmail(String vender_email){
        VenderEntity venderEntity = venderRepository.findByVenderEmail(vender_email)
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
        //중복아닐시
        //reqDTO(요청)객체 Entity형으로 전환
        VenderEntity venderEntity = modelMapper.map(venderReqDTO, VenderEntity.class);

        //DB에 등록(가입처리)
        venderRepository.save(venderEntity);

        //가입성공
        return true;
    }
    //회사회원 로그인처리------------------------------------------------------------------
    public boolean venderLogin(VenderReqDTO venderReqDTO){
        if ((venderRepository.existsByVenderEmail(venderReqDTO.getVenderEmail()))
                &&venderRepository.existsByVenderPasswd(venderReqDTO.getVenderPasswd())){
            //기업 회원 로그인 성공
            return true;
        }
        return false;
    }
    //회사 회원 탈퇴--------------------------------------------------------------------------
    public void venderLeave(String vender_email){
        //저장소에서 탈퇴하는 회사회원의 객체를 entity형태로 꺼냄
        VenderEntity venderEntity = venderRepository.findByVenderEmail(vender_email)
                .orElseThrow();

        venderEntity.setState("d");
    }


    //회사회원 정보 수정---------------------------------------------
    public VenderReqDTO venderUpdate(String vender_email, VenderReqDTO venderReqDTO){
        VenderEntity existsVender = venderRepository.findByVenderEmail(vender_email)
                .orElseThrow();

        //회사 비번 변경
        existsVender.setVenderPasswd(venderReqDTO.getVenderPasswd());
        //회사 이름 변경
        existsVender.setVenderName(venderReqDTO.getVenderName());
        //회사 정보 변경일
        existsVender.setUpdateDt(LocalDateTime.now());
        //회사 사진 변경(추후 커스텀 필요!!!!!!!!!!!!)-------------------------------------------
        existsVender.setPhoto(venderReqDTO.getPhoto());

        return modelMapper.map(existsVender, VenderReqDTO.class);
    }
}
