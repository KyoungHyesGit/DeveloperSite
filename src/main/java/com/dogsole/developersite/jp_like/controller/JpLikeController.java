package com.dogsole.developersite.jp_like.controller;


import com.dogsole.developersite.account.entity.user.UserEntity;
import com.dogsole.developersite.account.service.user.UserService;
import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import com.dogsole.developersite.jobPost.service.JobPostService;
import com.dogsole.developersite.jp_like.dto.JpLikeResDTO;
import com.dogsole.developersite.jp_like.service.JpLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jpLike")
public class JpLikeController {
    private final JpLikeService jpLikeService;
    private final UserService userService;
    private final JobPostService jobPostService;
    // 찜목록보기
    @GetMapping("/jpLikeList/{id}")
    public String getByUserId(@PathVariable Long id, Model model) {
        List<JpLikeResDTO> likeList = jpLikeService.getByUserId(id);
        model.addAttribute("likeList", likeList);
        return "jp_like/jpLikeList";
    }
    //들어 가나 테스트해봄
    @GetMapping("/addLike1")
    public String addLike1( Long userId, @RequestParam("jobPostId") Long jobPostId, Model model) {
        userId=1L;
        System.out.println(">>>>>>>>>>>>>>>"+jobPostId);
        jpLikeService.likeJob(userId, jobPostId);

        // 추가된 찜을 표시하는 페이지로 리디렉션
        return "redirect:/job_post/jobDetail/"+jobPostId;
    }

    //찜 or 취소


}