package com.dogsole.developersite.jobPost.controller;

import com.dogsole.developersite.common.dto.res.LovResDTO;
import com.dogsole.developersite.common.service.LovService;
import com.dogsole.developersite.jobPost.dto.req.JobPostTempReqDTO;
import com.dogsole.developersite.jobPost.dto.req.JobPostTempReqFormDTO;
import com.dogsole.developersite.jobPost.dto.res.JobPostTempResDTO;
import com.dogsole.developersite.jobPost.service.JobPostTempService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Transactional
@RequestMapping("/jobPostTemp")
public class JobPostTempController {

    private final JobPostTempService jobPostTempService;
    private final LovService lovService;

    @GetMapping("/vendersTempList/{id}")
    public String gotoAllJobPostTemp(Model model, @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                     @RequestParam(name = "size", required = false, defaultValue = "2") int size ,
                                     @PathVariable Long id) {
        Pageable pageable = PageRequest.of(page, size);
        Page<JobPostTempResDTO> tempList = jobPostTempService.getVendersPosts(id, pageable);
        model.addAttribute("tempList", tempList);
        return "/job_post_temp/show-venders-post-temp";
    }

    @GetMapping("/tempList")
    public String gotoAllJobPostTemp(Model model, @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                     @RequestParam(name = "size", required = false, defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page, size);
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

    @GetMapping("/edit/{id}")
    public String gotoEditJobPostTempPage(@PathVariable Long id, Model model){
        JobPostTempResDTO jobPostTemp = jobPostTempService.getJobTempPost(id);

        List<LovResDTO> postReqList = lovService.getLovByKind("post_req");
        List<LovResDTO> postWorkList = lovService.getLovByKind("post_work");

        model.addAttribute("jobPostTemp",jobPostTemp);

        model.addAttribute("postReqList",postReqList);
        model.addAttribute("postWorkList",postWorkList);
        return "/job_post_temp/edit-post-temp";
    }

    @PostMapping("/edit/{id}")
    public String editJobPostTemp(@ModelAttribute("jobPostTemp") @Valid JobPostTempReqFormDTO jobPostTemp, BindingResult result, Model model, HttpServletRequest request, @PathVariable Long id){
        if(result.hasErrors()){
            List<LovResDTO> postReqList = lovService.getLovByKind("post_req");
            List<LovResDTO> postWorkList = lovService.getLovByKind("post_work");

            model.addAttribute("jobPostTemp",jobPostTemp);

            model.addAttribute("postReqList",postReqList);
            model.addAttribute("postWorkList",postWorkList);

            return "/job_post_temp/edit-post-temp";
        }
        jobPostTemp.setIp(request.getRemoteAddr());
        jobPostTemp.setState("수정");

        jobPostTempService.updateJobPostTemp(id, jobPostTemp);
        return "redirect:/jobPostTemp/tempList";
    }

    @PostMapping("/delete/{id}")
    public String deleteJobPostTemp(@PathVariable Long id){
        jobPostTempService.deleteJobPostTemp(id);
        // TODO 사용자 정보에서 벤터 id빼서 홈으로 가기
        return "redirect:/jobPostTemp/vendersTempList/1";    }


}
