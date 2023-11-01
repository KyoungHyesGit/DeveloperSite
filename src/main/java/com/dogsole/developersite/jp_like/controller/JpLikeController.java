package com.dogsole.developersite.jp_like.controller;


import com.dogsole.developersite.jp_like.dto.JpLikeResDTO;
import com.dogsole.developersite.jp_like.service.JpLikeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jpLike")
public class JpLikeController {
    private final JpLikeService jpLikeService;
    // 찜목록보기
    @GetMapping("/jpLikeList/{id}")
    public String getByUserId(@PathVariable Long id, Model model) {
        List<JpLikeResDTO> likeList = jpLikeService.getByUserId(id);
        model.addAttribute("likeList", likeList);
        return "jp_like/jpLikeList";
    }
    // 찜하기
    @GetMapping("/addLike1")
    public String addLike1( @RequestParam("jobPostId") Long jobPostId, @RequestParam("venderId") Long venderId, HttpServletRequest request) {
        Long userId = Arrays.stream(request.getCookies())
                .filter(cookie -> "loginUserId".equals(cookie.getName())) // 원하는 쿠키 찾기
                .map(cookie -> Long.parseLong(cookie.getValue())) // 쿠키 값(String)을 Long으로 변환
                .findFirst() // 첫 번째 일치하는 쿠키 가져오기
                .orElse(null); // 쿠키를 찾지 못하면 기본값(null) 사용
        System.out.println("jobPostId :"+jobPostId);
        System.out.println("venderId :"+venderId);
        jpLikeService.likeJob(userId,venderId,jobPostId);
        // 추가된 찜을 표시하는 페이지로 리디렉션
        return "redirect:/job_post/jobDetail/"+jobPostId;
    }

    //찜 or 취소


}