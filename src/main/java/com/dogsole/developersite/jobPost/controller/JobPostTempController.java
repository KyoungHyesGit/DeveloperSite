package com.dogsole.developersite.jobPost.controller;

import com.dogsole.developersite.jobPost.dto.res.JobPostTempResDTO;
import com.dogsole.developersite.jobPost.service.JobPostTempService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/job_post_temp")
public class JobPostTempController {

    private final JobPostTempService jobPostTempService;

    @GetMapping("/tempList")
    public String gotoAllJobPostTemp(Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<JobPostTempResDTO> tempList = jobPostTempService.getAllPosts(pageable);
        model.addAttribute("tempList", tempList);
        return "/job_post_temp/tempList";
    }
}
