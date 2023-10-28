package com.dogsole.developersite.statistics.service;

import com.dogsole.developersite.account.repository.user.UserRepository;
import com.dogsole.developersite.common.exception.BusinessException;
import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import com.dogsole.developersite.jobPost.entity.JobPostTempEntity;
import com.dogsole.developersite.jobPost.repository.JobPostRepository;
import com.dogsole.developersite.jobPost.repository.JobPostTempRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final JobPostTempRepository jobPostTempRepository;
    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public List<Object[]> countUsersByCreatedAt() {
        return userRepository.countUsersByCreatedAt();
    }

    public List<Object[]> countUsersByBirthYear() {
        return userRepository.countUsersByBirthYear();
    }
}
