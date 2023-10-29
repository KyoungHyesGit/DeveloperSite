package com.dogsole.developersite.jp_apply.controller;

import com.dogsole.developersite.jp_apply.dto.JpApplyResDTO;
import com.dogsole.developersite.jp_apply.service.JpApplyService;
import lombok.RequiredArgsConstructor;
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

            jpApplyService.addAapplyJp(userId, venderId, jobPostId,resumeId);
        //지원목록 페이지로 이동
        return "redirect:/jpApply/jpApplyList/" + userId;
    }

    //지원삭제
    @PostMapping("/deleteApply")
    public String deleteApply(@RequestParam Long userId, @RequestParam Long venderId, @RequestParam Long jobPostId, Model model) {
        jpApplyService.delApllyJp(userId, venderId, jobPostId);

        //지원목록 페이지로 이동
        return "redirect:/jpApply/jpApplyList/" + userId;
    }
}
