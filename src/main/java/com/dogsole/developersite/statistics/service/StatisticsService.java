package com.dogsole.developersite.statistics.service;

import com.dogsole.developersite.account.repository.user.UserRepository;
import com.dogsole.developersite.statistics.dto.PontoResDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<PontoResDTO> countUsersByCreatedAt() {
        // 엔티티 리스트를 DTO 리스트로 변환
        List<PontoResDTO> pontoResDTOs = userRepository.countUsersByCreatedAt()
                .stream()
                .map(objects -> new PontoResDTO((String) objects[0],(Long) objects[1]))
                .collect(Collectors.toList());

        return pontoResDTOs;
    }

    public List<PontoResDTO> countUsersByBirthYear() {
        // 엔티티 리스트를 DTO 리스트로 변환
        List<PontoResDTO> pontoResDTOs = userRepository.countUsersByBirthYear()
                .stream()
                .map(objects -> new PontoResDTO((String) objects[0],(Long) objects[1]))
                .collect(Collectors.toList());

        return pontoResDTOs;
    }
}
