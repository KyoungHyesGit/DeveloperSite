package com.dogsole.developersite.common.service;

import com.dogsole.developersite.common.dto.res.LovResDTO;
import com.dogsole.developersite.common.repository.LovRepository;
import com.dogsole.developersite.jobPost.dto.req.JobPostTempReqDTO;
import com.dogsole.developersite.jobPost.dto.res.JobPostTempResDTO;
import com.dogsole.developersite.jobPost.entity.JobPostTempEntity;
import com.dogsole.developersite.jobPost.repository.JobPostTempRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LovService {
    private final LovRepository lovRepository;
    private final ModelMapper modelMapper;
    public List<LovResDTO> getLovByKind(String kind) {
        return lovRepository.findByKind(kind).stream()
                .map(lovEntity -> modelMapper.map(lovEntity, LovResDTO.class))
                .collect(Collectors.toList());
    }
}
