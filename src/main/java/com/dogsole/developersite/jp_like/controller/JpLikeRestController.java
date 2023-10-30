package com.dogsole.developersite.jp_like.controller;

import com.dogsole.developersite.common.exception.BusinessException;
import com.dogsole.developersite.jp_like.service.JpLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Transactional
@RequestMapping("/api")
public class JpLikeRestController {
    private final JpLikeService jpLikeService;

    @PostMapping("/addLike")
    public ResponseEntity<Map<String, String>> addLike(
            @RequestParam("userId") Long userId,
            @RequestParam("venderId") Long venderId,
            @RequestParam("jobPostId") Long jobPostId) {
        Map<String, String> response = new HashMap<>();
        try {
            jpLikeService.likeJob(userId, venderId, jobPostId);
            response.put("message", "success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BusinessException ex) {
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

}
