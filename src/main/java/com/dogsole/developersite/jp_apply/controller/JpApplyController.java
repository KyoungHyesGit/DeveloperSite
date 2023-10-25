package com.dogsole.developersite.jp_apply.controller;

import com.dogsole.developersite.jp_apply.dto.JpApplyResDTO;
import com.dogsole.developersite.jp_apply.service.JpApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jpApply")
public class JpApplyController {
    private final JpApplyService jpApplyService;

    @GetMapping("/jpApplyList/{id}")
    public String getByUserId(@PathVariable Long id, Model model) {
        JpApplyResDTO applyList = jpApplyService.getByUserId(id);
        model.addAttribute("applyList", applyList);
        return "jp_apply/jpApplyList";
    }
    //지원하기
    @PostMapping("/addApply")
    public String addApply(@RequestParam Long userId, @RequestParam Long venderId, @RequestParam Long jobPostId, @RequestParam Long jobPostTempId, Model model) {
        jpApplyService.addAapplyJp(userId, venderId, jobPostId,jobPostTempId);

        //지원목록 페이지로 이동
        return "redirect:/jpApply/jpApplyList/" + userId;
    }
    // -테스트
    @GetMapping("/addApplytest")
    public String addApplyt(Long userId, Long venderId, Long jobPostId, Long jobPostTempId, Model model) {
        boolean success = jpApplyService.addAapplyJp(userId, venderId, jobPostId, jobPostTempId);
        if (success) {
            return "jp_apply/jpApplyList"; // 저장 성공
        } else {
            return "jp_apply/jpApplyList";   // 저장 실패
        }
    }

    //지원삭제
    @PostMapping("/deleteApply")
    public String deleteApply(@RequestParam Long userId, @RequestParam Long venderId, @RequestParam Long jobPostId, @RequestParam Long jobPostTempId, Model model) {
        jpApplyService.delApllyJp(userId, venderId, jobPostId,jobPostTempId);

        //지원목록 페이지로 이동
        return "redirect:/jpApply/jpApplyList/" + userId;
    }
}
