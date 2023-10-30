package com.dogsole.developersite.jobPost.controller;

import com.dogsole.developersite.common.exception.BusinessException;
import com.dogsole.developersite.common.service.LovService;
import com.dogsole.developersite.jobPost.service.JobPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Transactional
@RequestMapping("/api/jobPostTemp")
public class JobPostTempRestController {

    private final JobPostService jobPostService;
    private final LovService lovService;

    @PostMapping("/allow/{id}")
    public ResponseEntity<Map<String, String>> addAllowJobPostTemp(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            jobPostService.allowReq(id);
            response.put("message", "success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BusinessException ex) {
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
