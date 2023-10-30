package com.dogsole.developersite.account.controller;


import com.dogsole.developersite.account.dto.user.UserReqDTO;
import com.dogsole.developersite.account.dto.user.UserResDTO;
import com.dogsole.developersite.account.dto.vender.VenderReqDTO;
import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.account.service.user.UserService;
import com.dogsole.developersite.account.service.vender.VenderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class SignupController {

    private final UserService userService;
    private final VenderService venderService;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    //일반유저의 회원가입 처리 ---------------------------------------------------------------------------
    //가입 페이지이동------------------------------------------------
    @GetMapping("/signuppage")
    public String signupPage(UserReqDTO userReqDTO){
        return "/account/signup-test";
    }

    //회원가입처리----------------------------------------------
    @PostMapping("/signuptest")
    public String signupTest(@Valid UserReqDTO userReqDTO, BindingResult result){
        //입력 항목 검증 시 오류발생 -> 다시 회원가입 페이지로
        if(result.hasErrors()){
           //검증오류 메세지 alert 띄우기(할 예정)
            return "/account/signup-test";
        }
        boolean isSignup = userService.userSignup(userReqDTO);
        if(isSignup){
            //가입성공 메세지 띄우기 alert
            System.out.println("sucess!!!!!!");
        }else{
            //동작 실패
            //가입 실패 메세지 띄욱 alert
            System.out.println("false!!!!!!");
            return "/account/signup-test";
        }
        return "/account/login-test";
    }

    //로그인페이지 이동--------------------------------------------------------
    @GetMapping("/loginpage")
    public String loginPage(UserReqDTO userReqDTO){
        return "/account/login-test";
    }

    //로그인 처리----------------------------------------------------------------
//    @PostMapping("/login")
//    public String userLogin(UserReqDTO userReqDTO){
//        //로그인 성공 시 true 실패 시 false
//        boolean isLogin = userService.userLogin(userReqDTO);
//
//        if(isLogin){
//            //로그인 성공 시 (로그인 성공 alert 띄우기)
//            System.out.println(isLogin);
//            return "redirect:/account/show";
//        }
//        else{
//            //로그인 실패 시 (로그인 실패 alert 띄우기)
//            System.out.println(isLogin);
//            return "/account/login-test";
//        }
//    }
    @PostMapping("/login")
    public String userLogin(UserReqDTO userReqDTO){
        //로그인 성공 시 true 실패 시 false
        boolean isLogin = userService.userLogin(userReqDTO);

        if(isLogin){
            //로그인 성공 시 (로그인 성공 alert 띄우기)
            System.out.println(isLogin);
            return "redirect:/account/show";
        }
        else{
            //로그인 실패 시 (로그인 실패 alert 띄우기)
            System.out.println(isLogin);
            return "/account/login-test";
        }
    }

    //테스트코드.(예비 메인페이지)--------------------------------------------------------------------------
    //유저로그인 성공시 목록띄우기
    @GetMapping("/show")
    public ModelAndView userShow(Model mode){
        List<UserResDTO> userResDTOList = userService.showUser();
        System.out.println(userResDTOList);
        return new ModelAndView("account/userList","users",userResDTOList);
    }

    //유저의 로그아웃 (할예정....)0------------------------------
    @PostMapping("/logout")
    public String userLogout(){

        return "";
    }

    //회원탈퇴 처리 (예정)-----------------------------------------------------------------------------
    @GetMapping("/delete/{id}")
    public UserReqDTO userLeave(UserReqDTO userReqDTO, String id){
        //탈퇴처리 (state 값 -> 'd'로
        userService.userLeave(userReqDTO.getUserEmail());
        //탈퇴회원 객체 반환
        return userReqDTO;
    }
    //회원탈퇴처리 테스트 컨트롤러
    @GetMapping("deletetest/{id}")
    public ResponseEntity<UserReqDTO> userLeaveTest(@PathVariable Long id, @RequestBody UserReqDTO userReqDTO){
        //탈퇴처리 (state값 ->d로
        userService.userLeaveTest(id);

        //@컨트롤러에서는 응답객체로!
        return new ResponseEntity<>(userReqDTO, HttpStatus.OK);
    }

    //회원 수정 처리

    //회원 수정처리 테스트
    @GetMapping("/update/{id}")
    public ResponseEntity<UserReqDTO> userUpdateTest(@PathVariable Long id, @RequestBody UserReqDTO userReqDTO){
        userService.userUpdateTest(id ,userReqDTO);

        return new ResponseEntity<>(userReqDTO, HttpStatus.OK);
    }

//-------------여기서 부터는 회사회원의 컨트롤러--------------------------------------------------------------------------------


    //회사회원의 회원가입처리----------------------------------------------------------------------
    //회사회원 회원가입 페이지로 이동
    @GetMapping("/signuppagev")
    public String signupPageV(VenderReqDTO venderReqDTO){
        return "account/signupv";
    }

    //회원가입처리----------------------------------------------------------------
    @PostMapping("/signuptestv")
    public String signupTestV(@Valid VenderReqDTO venderReqDTO, BindingResult result){
        if(result.hasErrors()){
            //검증오류 메세지 alert 띄우기(할 예정)
            return "/account/signupv";
        }
        boolean isSignup = venderService.venderSignup(venderReqDTO);

        if(isSignup){
            //가입성공 메세지 띄우기 alert

        }else{
            //가입 실패 메세지 띄우기 alert
            return "/account/signupv";
        }
        return "/account/loginv";
    }

    //회사회원 로그인페이지 이동--------------------------------------------------------
    @GetMapping("/loginpagev")
    public String loginPage(VenderReqDTO venderReqDTO){
        return "/account/loginv";
    }

    //회사회원 로그인 처리--------------------------------------------------------------------
//    @PostMapping("/loginv")
//    public String venderLogin(VenderReqDTO venderReqDTO){
//        boolean isLogin = venderService.venderLogin(venderReqDTO);
//        System.out.println(venderReqDTO);
//        System.out.println(isLogin);
//        if(isLogin){
//            //로그인 성공시 메인페이지로
//            System.out.println("기업로긴성공");
//            return "redirect:/account/showv";
//
//        }else{
//            //로글인 실패 alert띄우기.!!
//            System.out.println("기업로긴실패");
//            return "/account/loginv";
//        }
//    }

    //로그인 성공 시 페이지이동 처리(예비) ------------------------------------------------------
    @GetMapping("/showv")
    public ModelAndView venderShow(Model model){
        List<VenderResDTO> venderResDTOList = venderService.showVender();

        return new ModelAndView("account/venderList","venders",venderResDTOList);
    }

    //기업 회원탈퇴 처리 (예정)-----------------------------------------------------------------------------
    public VenderReqDTO userLeave(VenderReqDTO venderReqDTO){
        //탈퇴처리 (state 값 -> 'd'로 변경, 이 로직은 서비스에서 해결함.
        venderService.venderLeave(venderReqDTO.getVenderEmail());
        //탈퇴회원 객체 반환
        return venderReqDTO;
    }

//    @PostMapping("/login")
//    public String login(@RequestBody UserReqDTO userReqDTO, HttpServletResponse response) {
//        // 로그인 처리 로직을 수행합니다.
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userReqDTO.getUserEmail(), userReqDTO.getPasswd()));
//
//        if (authentication.isAuthenticated()) {
//            String myToken = jwtService.generateToken(userReqDTO.getUserEmail());
//
//            // 쿠키 생성
//            Cookie cookie = new Cookie("myTokenCookie", myToken);
//            cookie.setMaxAge(1800); // 쿠키의 만료 시간 (초) 설정, 이 경우 30분
//            cookie.setPath("/"); // 쿠키의 유효한 경로 설정, 필요에 따라 수정 가능
//
//            // 쿠키를 클라이언트로 보내기
//            response.addCookie(cookie);
//            return "/index"; // 로그인 성공 후 이동할 페이지
//        } else {
//            throw new UsernameNotFoundException("invalid user request !");
//        }
//    }
}