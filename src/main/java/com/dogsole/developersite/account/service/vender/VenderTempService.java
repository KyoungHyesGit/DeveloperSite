package com.dogsole.developersite.account.service.vender;


import com.dogsole.developersite.account.dto.vender.VenderReqDTO;
import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.account.dto.vender.VenderTempReqDTO;
import com.dogsole.developersite.account.dto.vender.VenderTempResDTO;
import com.dogsole.developersite.account.entity.vender.VenderEntity;
import com.dogsole.developersite.account.entity.vender.VenderTempEntity;
import com.dogsole.developersite.account.repository.vender.VenderRepository;
import com.dogsole.developersite.account.repository.vender.VenderTempRepository;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class VenderTempService {
    private final VenderTempRepository venderTempRepository;
    private final ModelMapper modelMapper;

    public VenderTempResDTO createVenderTemp(VenderTempReqDTO venderTempReqDTO) {
        VenderTempEntity venderTempEntity =
                modelMapper.map(venderTempReqDTO, VenderTempEntity.class);

        venderTempRepository.save(venderTempEntity);

        return modelMapper.map(venderTempEntity, VenderTempResDTO.class);
    }

    public boolean isExistName(String name){
        return venderTempRepository.existsByName(name);
    }

}
