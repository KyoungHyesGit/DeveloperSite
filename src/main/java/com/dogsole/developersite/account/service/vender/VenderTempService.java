package com.dogsole.developersite.account.service.vender;


import com.dogsole.developersite.account.dto.vender.VenderReqDTO;
import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.account.dto.vender.VenderTempReqDTO;
import com.dogsole.developersite.account.dto.vender.VenderTempResDTO;
import com.dogsole.developersite.account.entity.user.UserEntity;
import com.dogsole.developersite.account.entity.vender.VenderEntity;
import com.dogsole.developersite.account.entity.vender.VenderTempEntity;
import com.dogsole.developersite.account.repository.vender.VenderRepository;
import com.dogsole.developersite.account.repository.vender.VenderTempRepository;
import com.dogsole.developersite.account.service.user.UserService;
import com.dogsole.developersite.jobPost.dto.req.JobPostTempReqDTO;
import com.dogsole.developersite.jobPost.dto.res.JobPostTempResDTO;
import com.dogsole.developersite.jobPost.entity.JobPostTempEntity;
import com.dogsole.developersite.jwt.entity.TokenEntity;
import com.dogsole.developersite.jwt.provider.JwtTokenProvider;
import com.dogsole.developersite.jwt.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class VenderTempService {
    private final VenderTempRepository venderTempRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public VenderTempResDTO createVenderTemp(VenderTempReqDTO venderTempReqDTO, MultipartFile file, Long userId) throws Exception {
        VenderTempEntity venderTempEntity =
                modelMapper.map(venderTempReqDTO, VenderTempEntity.class);

        //사진 업로드
        String projectPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\img\\vender";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid+"_"+file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);
        venderTempEntity.setPhoto(fileName);
        venderTempEntity.setUserId(userId);

        venderTempEntity.setState("등록");

        venderTempRepository.save(venderTempEntity);

        return modelMapper.map(venderTempEntity, VenderTempResDTO.class);
    }

    public boolean isExistName(String name){
        return venderTempRepository.existsByName(name);
    }

    @Transactional(readOnly = true)
    public Page<VenderTempResDTO> showVenderTemp(Pageable pageable){
        Page<VenderTempEntity> venderTempEntityList=venderTempRepository.findAll(pageable);
        //화면에 뿌려주기 위해
        Page<VenderTempResDTO> venderTempResDTOList = venderTempEntityList
                .map(vender -> modelMapper.map(vender, VenderTempResDTO.class));

        return venderTempResDTOList;
    }
}
