package com.dogsole.developersite.userResume.controller;

import com.dogsole.developersite.userResume.dto.UserResumeReqDTO;
import com.dogsole.developersite.userResume.dto.UserResumeResDTO;
import com.dogsole.developersite.userResume.entity.UserResumeEntity;
import com.dogsole.developersite.userResume.service.UserResumeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
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
    public String showCreateResumePage(HttpServletRequest request,Model model) {
        Long userId = Arrays.stream(request.getCookies())
                .filter(cookie -> "loginUserId".equals(cookie.getName())) // 원하는 쿠키 찾기
                .map(cookie -> Long.parseLong(cookie.getValue())) // 쿠키 값(String)을 Long으로 변환
                .findFirst() // 첫 번째 일치하는 쿠키 가져오기
                .orElse(null); // 쿠키를 찾지 못하면 기본값(null) 사용
        model.addAttribute("userId",userId);
        return "userResume/createResume";
    }
    //이력서 작성
    @PostMapping("/createResume")
    public String createResume(@ModelAttribute UserResumeReqDTO userResumeReqDTO,@RequestParam("photo") MultipartFile photo,HttpServletRequest request) throws Exception {
        Long userId = Arrays.stream(request.getCookies())
                .filter(cookie -> "loginUserId".equals(cookie.getName())) // 원하는 쿠키 찾기
                .map(cookie -> Long.parseLong(cookie.getValue())) // 쿠키 값(String)을 Long으로 변환
                .findFirst() // 첫 번째 일치하는 쿠키 가져오기
                .orElse(null); // 쿠키를 찾지 못하면 기본값(null) 사용
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
    @GetMapping("/delete/{id}")
    public String deleteResume(@PathVariable Long id, HttpServletRequest request) {
        // 이력서 정보를 데이터베이스에서 불러옵니다.
        userResumeService.deleteResumeById(id);
        Cookie[] cookies = request.getCookies();
        Long userId = Arrays.stream(request.getCookies())
                .filter(cookie -> "loginUserId".equals(cookie.getName())) // 원하는 쿠키 찾기
                .map(cookie -> Long.parseLong(cookie.getValue())) // 쿠키 값(String)을 Long으로 변환
                .findFirst() // 첫 번째 일치하는 쿠키 가져오기
                .orElse(null); // 쿠키를 찾지 못하면 기본값(null) 사용
        return "redirect:/userResume/"+userId;
    }
    //지원하기 선택후 이력서 선택
    @GetMapping("/selectResume/{id}")
    public String selectResume(@PathVariable Long id, @RequestParam("venderId") Long venderId, @RequestParam("jobPostId") Long jobPostId,
                               Model model){

        List<UserResumeResDTO> userResumes = userResumeService.getUserResumesByUserId(id);
        model.addAttribute("userResumes", userResumes);
        model.addAttribute("userId", id);

        model.addAttribute("venderId", venderId);
        model.addAttribute("jobPostId", jobPostId);
        return "/userResume/userResumeSelection";
    }
}