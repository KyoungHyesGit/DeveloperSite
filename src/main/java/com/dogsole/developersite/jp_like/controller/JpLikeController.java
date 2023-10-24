package com.dogsole.developersite.jp_like.controller;


import com.dogsole.developersite.jp_like.dto.JpLikeResDTO;
import com.dogsole.developersite.jp_like.service.JpLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jpLike")
public class JpLikeController {
    private final JpLikeService jpLikeService;

    @PostMapping("/addLike")
    public String addLike(@RequestParam Long userId,@RequestParam Long venderId, @RequestParam Long jobPostId, Model model) {
        // JpLikeService를 사용하여 찜 추가
        jpLikeService.addLike(userId,venderId, jobPostId);

        // 추가된 찜을 표시하는 페이지로 리디렉션
        return "redirect:/jpLike/jpLikeList";
    }
    @GetMapping("/addLike1")
    public String addLike1( Long userId, Long venderId, Long jobPostId, Model model) {
        // JpLikeService를 사용하여 찜 추가
        jpLikeService.addLike(userId,venderId, jobPostId);

        // 추가된 찜을 표시하는 페이지로 리디렉션
        return "redirect:/jpLike/jpLikeList";
    }
    @GetMapping("/jpLikeList")
    public String getByUserId(@PathVariable Long id, Model model){
        JpLikeResDTO likeList = jpLikeService.getByUserId(id);
        model.addAttribute("likeList",likeList);
        return "likeList";
    }
}