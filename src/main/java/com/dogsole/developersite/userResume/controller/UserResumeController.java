package com.dogsole.developersite.userResume.controller;

import com.dogsole.developersite.userResume.dto.UserResumeReqDTO;
import com.dogsole.developersite.userResume.dto.UserResumeResDTO;
import com.dogsole.developersite.userResume.entity.UserResumeEntity;
import com.dogsole.developersite.userResume.service.UserResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/userResume")
public class UserResumeController {
    private final UserResumeService userResumeService;

    @GetMapping("/{userId}")
    public String getUserResumes(@PathVariable Long userId, Model model) {
        List<UserResumeResDTO> userResumes = userResumeService.getUserResumesByUserId(userId);
        model.addAttribute("userResumes", userResumes);
        return "/userResume/userResumeList";
    }
    //이력서 작성페이지로 이동
    @GetMapping("/showCreateResumePage")
    public String showCreateResumePage() {
        return "userResume/createResume";
    }


}
