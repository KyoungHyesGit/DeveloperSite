package com.dogsole.developersite.jobPost.controller;

import com.dogsole.developersite.common.dto.res.LovResDTO;
import com.dogsole.developersite.common.service.LovService;
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
    private final LovService lovService;

    @GetMapping("/jobList")
    public String allJobPost(Model model, @PageableDefault(size = 10) Pageable pageable){
        Page<JobPostResDTO> jobList = jobPostService.getAllPost(pageable);
        model.addAttribute("jobList", jobList);
        return "/job_post/jobList";
    }

    @GetMapping("/jobDetail/{id}")
    public String JobDetail(@PathVariable Long id, Model model){
        JobPostResDTO jobDetail = jobPostService.getJobDetail(id);
        List<LovResDTO> postReqList = lovService.getLovByKind("post_req");
        List<LovResDTO> postWorkList = lovService.getLovByKind("post_work");

        model.addAttribute("jobDetail", jobDetail);
        model.addAttribute("postReqList",postReqList);
        model.addAttribute("postWorkList",postWorkList);

        return "/job_post/jobDetail";
    }

}
