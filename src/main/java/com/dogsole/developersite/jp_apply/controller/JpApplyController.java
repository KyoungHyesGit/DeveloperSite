package com.dogsole.developersite.jp_apply.controller;

import com.dogsole.developersite.jp_apply.dto.JpApplyResDTO;
import com.dogsole.developersite.jp_apply.service.JpApplyService;
import com.dogsole.developersite.userResume.dto.UserResumeResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jpApply")
public class JpApplyController {
    private final JpApplyService jpApplyService;

    @GetMapping("/jpApplyList/{id}")
    public String getByUserId(@PathVariable Long id, Model model) {
        List<JpApplyResDTO> applyList = jpApplyService.getByUserId(id);
        model.addAttribute("applyList", applyList);
        return "jp_apply/jpApplyList";
    }
    //지원하기
    @GetMapping("/addApply")
    public String addApply( Long userId, @RequestParam("venderId") Long venderId, @RequestParam("jobPostId") Long jobPostId, @RequestParam("resumeId") Long resumeId, Model model) {

        userId=1L;
        System.out.println(">>>>>"+jobPostId);
        jpApplyService.addAapplyJp(userId, venderId, jobPostId,resumeId);
        //지원목록 페이지로 이동
        return "redirect:/jpApply/jpApplyList/" + userId;
    }

    //지원삭제
    @GetMapping("/deleteApply")
//    public String deleteApply(@RequestParam Long userId, @RequestParam Long jobPostId, Model model) {
    //임시 유저아이디 지정
    public String deleteApply( Long userId, @RequestParam("jpApplyId") Long jpApplyId, Model model) {
        userId=1L;
        System.out.println("삭제 컨트롤러");
        jpApplyService.delApllyJp(jpApplyId);

        //지원목록 페이지로 이동
        return "redirect:/jpApply/jpApplyList/" + userId;
    }
    @GetMapping("/postResumeList/{id}")
    public String getResumeId(@PathVariable Long id, Model model, @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                              @RequestParam(name = "size", required = false, defaultValue = "2") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<JpApplyResDTO> postResumeList = jpApplyService.getPostResumeList(id,pageable);
        model.addAttribute("applyList",postResumeList);
            return "/jp_apply/jpApplyPostList";
    }
}