package com.dogsole.developersite.jobPost.controller;

import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.account.dto.vender.VenderTempResDTO;
import com.dogsole.developersite.account.service.vender.VenderService;
import com.dogsole.developersite.common.dto.res.LovResDTO;
import com.dogsole.developersite.common.service.LovService;
import com.dogsole.developersite.jobPost.dto.res.JobPostResDTO;
import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import com.dogsole.developersite.jobPost.service.JobPostService;

import com.dogsole.developersite.jp_apply.service.JpApplyService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/job_post")
public class JobPostController {
    private final JobPostService jobPostService;
    private final LovService lovService;
    private final VenderService venderService;
    private final JpApplyService jpApplyService;

    // 전체공고리스트(main)
    @GetMapping("/jobList")
    public String allJobPost(Model model, @PageableDefault(size = 10) Pageable pageable){
        Page<JobPostResDTO> jobList = jobPostService.getAllPost(pageable);
        model.addAttribute("jobList", jobList);
        return "/job_post/jobList";
    }

    @GetMapping("/venderJobList/{venderId}")
    public String venderJobList(@PathVariable Long venderId, Model model, @PageableDefault(size = 10) Pageable pageable){
        Page<JobPostResDTO> jobList = jobPostService.getVenderPost(venderId, pageable);
        model.addAttribute("jobList", jobList);
        return "/job_post/venderJobList";
    }


    // 공고 상세페이지
    @GetMapping("/jobDetail/{id}")
    public String JobDetail(@PathVariable Long id, Model model, HttpServletRequest request){
        JobPostResDTO jobDetail = jobPostService.getJobDetail(id);
        List<LovResDTO> postReqList = lovService.getLovByKind("post_req");
        List<LovResDTO> postWorkList = lovService.getLovByKind("post_work");

        try{
            Long userId = Arrays.stream(request.getCookies())
                    .filter(cookie -> "loginUserId".equals(cookie.getName())) // 원하는 쿠키 찾기
                    .map(cookie -> Long.parseLong(cookie.getValue())) // 쿠키 값(String)을 Long으로 변환
                    .findFirst() // 첫 번째 일치하는 쿠키 가져오기
                    .orElse(null); // 쿠키를 찾지 못하면 기본값(null) 사용
            boolean isAlreadyApplied = jpApplyService.isAlreadyApplied(userId, id);
            model.addAttribute("isAlreadyApplied",isAlreadyApplied);
            model.addAttribute("userId",userId);
        }catch (Exception e){
            model.addAttribute("isAlreadyApplied",false);
        }

        model.addAttribute("jobDetail", jobDetail);
        model.addAttribute("postReqList",postReqList);
        model.addAttribute("postWorkList",postWorkList);
        return "/job_post/jobDetail";
    }

    // 검색
    @GetMapping("/jobSearch")
    public String getSearchJobPost(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "sortOption", required = false, defaultValue = "default") String sortOption,
            @PageableDefault(size = 10) Pageable pageable,
            Model model
    ) {
        Page<JobPostResDTO> jobSearch;
        if ("dateAsc".equals(sortOption)) {
            jobSearch = jobPostService.getAllPostByDateAsc(keyword, pageable);
        } else if ("etDesc".equals(sortOption)) {
            jobSearch = jobPostService.getEndTimeAsc(keyword, pageable);
        } else {
            jobSearch = jobPostService.getSearchJobPost(keyword, pageable);
        }

        model.addAttribute("jobSearch", jobSearch);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortOption", sortOption);

        return "/job_post/jobSearch";
    }

    // 검색-등록순
    @GetMapping("/listByDateAsc")
    public String listByDateAsc(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "sortOption", required = false) String sortOption,
            Model model,
            @PageableDefault(page = 0, size = 5, sort = "createDt", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<JobPostResDTO> list;
        if ("dateAsc".equals(sortOption)) {
            list = jobPostService.getAllPostByDateAsc(keyword, pageable);
        } else {
            list = jobPostService.getSearchJobPost(keyword, pageable);
        }
        addAttributes(model, list, keyword, sortOption);
        return "/job_post/jobSearch";
    }

    // 검색-마감일순
    @GetMapping("/listByEndTimeAsc")
    public String listByEndTimeAsc(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "sortOption", required = false) String sortOption,
            Model model,
            @PageableDefault(page = 0, size = 5, sort = "createDt", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<JobPostResDTO> list;
        if ("etDesc".equals(sortOption)) {
            list = jobPostService.getEndTimeAsc(keyword, pageable);
        } else {
            list = jobPostService.getSearchJobPost(keyword, pageable);
        }
        addAttributes(model, list, keyword, sortOption);
        return "/job_post/jobSearch";
    }

    private void addAttributes(Model model, Page<JobPostResDTO> list, String keyword, String sortOption) {
        model.addAttribute("jobSearch", list);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortOption", sortOption);
    }

    @GetMapping("/venderList")
    public ModelAndView venderShow(Model model, @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                   @RequestParam(name = "size", required = false, defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<VenderResDTO> venderResDTOList = venderService.showVender(pageable);
        return new ModelAndView("job_post/venderList","venders",venderResDTOList);
    }


}
