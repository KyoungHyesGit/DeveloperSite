package com.dogsole.developersite.jp_like.controller;


import com.dogsole.developersite.jpLike.dto.JpLikeResDTO;
import com.dogsole.developersite.jpLike.service.JpLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jpLike")
public class JpLikeController {
    private final JpLikeService jpLikeService;

    @GetMapping("/jpLikeList")
    public String getByUserId(@PathVariable int id, Model model){
        JpLikeResDTO likeList = jpLikeService.getByUserId(id);
        model.addAttribute("likeList",likeList);
        return "likeList";
    }
}\