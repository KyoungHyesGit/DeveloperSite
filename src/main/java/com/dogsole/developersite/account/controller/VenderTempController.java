package com.dogsole.developersite.account.controller;

import com.dogsole.developersite.account.dto.vender.VenderReqDTO;
import com.dogsole.developersite.account.dto.vender.VenderTempReqDTO;
import com.dogsole.developersite.account.service.vender.VenderTempService;
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
@RequestMapping("/vender")
public class VenderTempController {

    private final VenderTempService venderTempService;

    @GetMapping("/add")
    public String gotoAddJobPostTempPage(VenderTempReqDTO venderTempReqDTO, Model model){
        return "/account/add_vender_temp";
    }

    @PostMapping("/add")
    public String addJobPostTemp(@ModelAttribute @Valid VenderTempReqDTO venderTempReqDTO, BindingResult result, Model model, HttpServletRequest request){
        if (result.hasErrors()) {
            return "/job_post_temp/add_vender_temp";
        }


        venderTempService.createVenderTemp(venderTempReqDTO);

        return "redirect:/jobPostTemp/vendersTempList/1";
    }

    @GetMapping("/edit/{id}")
    public String gotoEditJobPostTempPage(@PathVariable Long id, Model model){
//        JobPostTempResDTO jobPostTemp = jobPostTempService.getJobTempPost(id);

//        model.addAttribute("jobPostTemp",jobPostTemp);

        return "/job_post_temp/edit-post-temp";
    }

    @PostMapping("/edit/{id}")
    public String editJobPostTemp(@ModelAttribute("jobPostTemp") @Valid JobPostTempReqFormDTO jobPostTemp, BindingResult result, Model model, HttpServletRequest request, @PathVariable Long id){
        if(result.hasErrors()){

            model.addAttribute("jobPostTemp",jobPostTemp);


            return "/job_post_temp/edit-post-temp";
        }
        jobPostTemp.setIp(request.getRemoteAddr());
        jobPostTemp.setState("수정");
        jobPostTemp.setReqState("");

//        jobPostTempService.updateJobPostTemp(id, jobPostTemp);
        return "redirect:/jobPostTemp/vendersTempList/1";
    }

    @PostMapping("/delete/{id}")
    public String deleteJobPostTemp(@PathVariable Long id){
//        jobPostTempService.deleteJobPostTemp(id);
        // TODO 사용자 정보에서 벤터 id빼서 홈으로 가기
        return "redirect:/jobPostTemp/vendersTempList/1";    }


}
