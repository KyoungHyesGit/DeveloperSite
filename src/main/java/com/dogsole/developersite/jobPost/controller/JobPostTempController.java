package com.dogsole.developersite.jobPost.controller;

import com.dogsole.developersite.common.dto.res.LovResDTO;
import com.dogsole.developersite.common.service.LovService;
import com.dogsole.developersite.jobPost.dto.req.JobPostTempReqDTO;
import com.dogsole.developersite.jobPost.dto.res.JobPostTempResDTO;
import com.dogsole.developersite.jobPost.service.JobPostTempService;
import com.dogsole.developersite.vender.dto.req.VenderReqDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jobPostTemp")
public class JobPostTempController {

    private final JobPostTempService jobPostTempService;
    private final LovService lovService;

    @GetMapping("/tempList")
    public String gotoAllJobPostTemp(Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<JobPostTempResDTO> tempList = jobPostTempService.getAllPosts(pageable);
        model.addAttribute("tempList", tempList);
        return "/job_post_temp/show-all-post-temp";
    }

    @GetMapping("/add")
    public String gotoAddJobPostTempPage(JobPostTempReqDTO jobPostTempReqDTO, Model model){
        List<LovResDTO> postReqList = lovService.getLovByKind("post_req");
        List<LovResDTO> postWorkList = lovService.getLovByKind("post_work");
        model.addAttribute("postReqList",postReqList);
        model.addAttribute("postWorkList",postWorkList);
        return "/job_post_temp/add-post-temp";
    }

    @PostMapping("/add")
    public String addJobPostTemp(@ModelAttribute @Valid JobPostTempReqDTO jobPostTempReqDTO, BindingResult result, Model model, HttpServletRequest request){
        if (result.hasErrors()) {
            List<LovResDTO> postReqList = lovService.getLovByKind("post_req");
            List<LovResDTO> postWorkList = lovService.getLovByKind("post_work");
            model.addAttribute("postReqList",postReqList);
            model.addAttribute("postWorkList",postWorkList);
            return "/job_post_temp/add-post-temp";
        }
        jobPostTempReqDTO.setIp(request.getRemoteAddr());
        jobPostTempReqDTO.setState("등록");


        jobPostTempService.createJobTempPost(jobPostTempReqDTO);

        return "redirect:/jobPostTemp/tempList";
    }
}
