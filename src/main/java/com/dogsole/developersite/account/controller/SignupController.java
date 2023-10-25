package com.dogsole.developersite.account.controller;


import com.dogsole.developersite.account.dto.user.UserReqDTO;
import com.dogsole.developersite.account.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class SignupController {

    private final UserService userService;

    //가입 페이지이동
    @GetMapping("/signuppage")
    public String signupPage(UserReqDTO userReqDTO){
        return "/account/signup-test";
    }

    //회원가입처리
    @PostMapping("/signuptest")
    public String signupTest(@Valid UserReqDTO userReqDTO, BindingResult result){
        //입력 항목 검증 시 오류발생 -> 다시 회원가입 페이지로
        if(result.hasErrors()){
            System.out.println(userReqDTO +"FAIL");
            //가입 실패메세지 띄우기 (할 예정)
            return "/account/signup-test";
        }
        boolean isSignup = userService.userSignup(userReqDTO);
        if(isSignup){
            System.out.println("sucess!!!!!!");
        }else{
            //동작 실패
            System.out.println("false!!!!!!");
        }
        System.out.println(userReqDTO +"SUCESS");
        //model.addAttribute("userReqDTO",userReqDTO);
        return "/account/login-test";
    }
}
