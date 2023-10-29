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
    //이력서 작성
    @PostMapping("/createResume")
    public String createResume(@ModelAttribute UserResumeReqDTO userResumeReqDTO,@RequestParam("photo") MultipartFile photo,Long userId) throws Exception {
        UserResumeEntity userResumeEntity = userResumeService.saveUserResume(userResumeReqDTO,photo,userId);
        return "redirect:/userResume/" + userResumeEntity.getUserEntity().getUserId();
    }
    //이력서 디테일폼으로 아동
    @GetMapping("/showDetailForm/{id}")
    public String showDetailForm(@PathVariable Long id, Model model) {
        // 이력서 정보를 데이터베이스에서 불러옵니다.
        UserResumeResDTO userResume = userResumeService.getUserResumeByid(id);

        // 수정 폼에 이력서 정보를 전달합니다.
        model.addAttribute("resume", userResume);

        return "/userResume/userResumeDetail"; // 디테일 폼 이름
    }
    //수정폼으로 이동
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        // 이력서 정보를 데이터베이스에서 불러옵니다.
        UserResumeResDTO userResume = userResumeService.getUserResumeByid(id);

        // 수정 폼에 이력서 정보를 전달합니다.
        model.addAttribute("resume", userResume);

        return "/userResume/userResumeEdit"; // 수정 폼 이름
    }
    @PostMapping("/delete/{id}")
    public String deleteResume(@PathVariable Long id) {
        // 이력서 정보를 데이터베이스에서 불러옵니다.
        userResumeService.deleteResumeById(id);
        Long userId = userResumeService.getUserIdByResumeId(id);

        return "redirect:/userResume/"+userId;
    }
    @GetMapping("/selectResume/{id}")
    public String selectResume(Long userId, @RequestParam("venderId") Long venderId, @RequestParam("jobPostId") Long jobPostId,
                                Model model){
        //userId 임시 지정
        userId=1L;
        List<UserResumeResDTO> userResumes = userResumeService.getUserResumesByUserId(userId);
        model.addAttribute("userResumes", userResumes);
        model.addAttribute("userId", userId);
        model.addAttribute("venderId", venderId);
        model.addAttribute("jobPostId", jobPostId);
        return "/userResume/userResumeSelection";
    }
}
