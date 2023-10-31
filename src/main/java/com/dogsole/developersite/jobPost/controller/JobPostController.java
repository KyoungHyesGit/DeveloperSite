package com.dogsole.developersite.jobPost.controller;

import com.dogsole.developersite.common.dto.res.LovResDTO;
import com.dogsole.developersite.common.service.LovService;
import com.dogsole.developersite.jobPost.dto.res.JobPostResDTO;
import com.dogsole.developersite.jobPost.service.JobPostService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/job_post")
public class JobPostController {
    private final JobPostService jobPostService;
    private final LovService lovService;

    @GetMapping("/jobList")
    public String allJobPost(Model model, @PageableDefault(size = 10) Pageable pageable){
        Page<JobPostResDTO> jobList = jobPostService.getAllPost(pageable);
        model.addAttribute("jobList", jobList);
        return "/job_post/jobList";
    }

    @GetMapping("/jobDetail/{id}")
    public String JobDetail(@PathVariable Long id, Model model, HttpServletRequest request){
        JobPostResDTO jobDetail = jobPostService.getJobDetail(id);
        List<LovResDTO> postReqList = lovService.getLovByKind("post_req");
        List<LovResDTO> postWorkList = lovService.getLovByKind("post_work");

        Cookie[] cookies = request.getCookies();
        Long userId = Arrays.stream(request.getCookies())
                .filter(cookie -> "loginUserId".equals(cookie.getName())) // 원하는 쿠키 찾기
                .map(cookie -> Long.parseLong(cookie.getValue())) // 쿠키 값(String)을 Long으로 변환
                .findFirst() // 첫 번째 일치하는 쿠키 가져오기
                .orElse(null); // 쿠키를 찾지 못하면 기본값(null) 사용
        model.addAttribute("userId",userId);
        model.addAttribute("jobDetail", jobDetail);
        model.addAttribute("postReqList",postReqList);
        model.addAttribute("postWorkList",postWorkList);
        return "/job_post/jobDetail";
    }

}
