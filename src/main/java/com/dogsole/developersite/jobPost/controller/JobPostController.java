package com.dogsole.developersite.jobPost.controller;

import com.dogsole.developersite.jobPost.dto.res.JobPostResDTO;
import com.dogsole.developersite.jobPost.service.JobPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/job_post")
public class JobPostController {
    private final JobPostService jobPostService;

    @GetMapping("/jobList")
    public String allJobPost(Model model, @PageableDefault(size = 10) Pageable pageable){
        Page<JobPostResDTO> jobList = jobPostService.getAllPost(pageable);
        model.addAttribute("jobList", jobList);
        return "/job_post/jobList";
    }


}
