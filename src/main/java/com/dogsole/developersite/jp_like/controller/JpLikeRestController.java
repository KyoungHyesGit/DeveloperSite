package com.dogsole.developersite.jp_like.controller;

import com.dogsole.developersite.common.exception.BusinessException;
import com.dogsole.developersite.jp_like.dto.JpLikeResDTO;
import com.dogsole.developersite.jp_like.entity.JpLikeEntity;
import com.dogsole.developersite.jp_like.service.JpLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequiredArgsConstructor
@Transactional
@RequestMapping("/api")
public class JpLikeRestController {
    private final JpLikeService jpLikeService;
//찜등록
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
//찜 되있나 확인
    @GetMapping("/checkLike")
    public ResponseEntity<Boolean> checkLike(
            @RequestParam("userId") Long userId,
            @RequestParam("venderId") Long venderId,
            @RequestParam("jobPostId") Long jobPostId) {
        try {
            boolean isLiked = jpLikeService.isJobLiked (userId, jobPostId);
            return new ResponseEntity<>(isLiked, HttpStatus.OK);
        } catch (BusinessException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
// 찜 삭제
@PostMapping("/removeLike")
public ResponseEntity<Map<String, String>> removeLike(
        @RequestParam("userId") Long userId,
        @RequestParam("venderId") Long venderId,
        @RequestParam("jobPostId") Long jobPostId) {
    Map<String, String> response = new HashMap<>();

    try {
        // 여기에서 jpLikeService를 사용하여 찜 데이터를 삭제하는 메서드를 호출
        jpLikeService.removeLike(userId, jobPostId);

        response.put("message", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (BusinessException ex) {
        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}


}
