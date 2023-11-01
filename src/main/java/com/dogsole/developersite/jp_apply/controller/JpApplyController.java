package com.dogsole.developersite.jp_apply.controller;

import com.dogsole.developersite.jp_apply.dto.JpApplyResDTO;
import com.dogsole.developersite.jp_apply.service.JpApplyService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
    public String addApply(HttpServletRequest request, @RequestParam("venderId") Long venderId, @RequestParam("jobPostId") Long jobPostId, @RequestParam("resumeId") Long resumeId, Model model) {
        request.getCookies();
        Long userId = Arrays.stream(request.getCookies())
                .filter(cookie -> "loginUserId".equals(cookie.getName())) // 원하는 쿠키 찾기
                .map(cookie -> Long.parseLong(cookie.getValue())) // 쿠키 값(String)을 Long으로 변환
                .findFirst() // 첫 번째 일치하는 쿠키 가져오기
                .orElse(null); // 쿠키를 찾지 못하면 기본값(null) 사용

            jpApplyService.addAapplyJp(userId, venderId, jobPostId,resumeId);
            //지원목록 페이지로 이동
            return "redirect:/jpApply/jpApplyList/" + userId;

    }

    //지원삭제
    @GetMapping("/deleteApply")
//    public String deleteApply(@RequestParam Long userId, @RequestParam Long jobPostId, Model model) {
    //임시 유저아이디 지정
    public String deleteApply( @RequestParam("jpApplyId") Long jpApplyId, HttpServletRequest request) {
        Long userId = Arrays.stream(request.getCookies())
                .filter(cookie -> "loginUserId".equals(cookie.getName())) // 원하는 쿠키 찾기
                .map(cookie -> Long.parseLong(cookie.getValue())) // 쿠키 값(String)을 Long으로 변환
                .findFirst() // 첫 번째 일치하는 쿠키 가져오기
                .orElse(null); // 쿠키를 찾지 못하면 기본값(null) 사용
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