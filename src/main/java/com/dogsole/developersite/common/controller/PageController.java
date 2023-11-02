package com.dogsole.developersite.common.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class PageController {
    @GetMapping("/userMypage")
    public String userMypage(Model model,HttpServletRequest request){
        Long userId = Arrays.stream(request.getCookies())
                .filter(cookie -> "loginUserId".equals(cookie.getName())) // 원하는 쿠키 찾기
                .map(cookie -> Long.parseLong(cookie.getValue())) // 쿠키 값(String)을 Long으로 변환
                .findFirst() // 첫 번째 일치하는 쿠키 가져오기
                .orElse(null); // 쿠키를 찾지 못하면 기본값(null) 사용
        Long venderId = Arrays.stream(request.getCookies())
                .filter(cookie -> "loginVenderId".equals(cookie.getName()))
                .map(cookie -> Long.parseLong(cookie.getValue()))
                .findFirst()
                .orElse(null); // 쿠키를 찾지 못하면 기본값(null) 사용

        model.addAttribute("venderId",venderId);
        model.addAttribute("userId",userId);
        System.out.println("userId  = "+userId);
        System.out.println("venderId  = "+venderId);
        return "userMypage";
    }

    @GetMapping("/adminPage")
    public String adminPage(){
        return "adminPage";
    }
}
