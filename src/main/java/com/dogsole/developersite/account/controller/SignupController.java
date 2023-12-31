package com.dogsole.developersite.account.controller;


import com.dogsole.developersite.account.dto.user.UserReqDTO;
import com.dogsole.developersite.account.dto.user.UserResDTO;
import com.dogsole.developersite.account.dto.vender.VenderReqDTO;
import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.account.service.user.UserService;
import com.dogsole.developersite.account.service.vender.VenderService;

import com.dogsole.developersite.jwt.provider.JwtTokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class SignupController {

    private final UserService userService;
    private final VenderService venderService;
    private final JwtTokenProvider jwtTokenProvider;


//일반유저의 회원가입 처리 ---------------------------------------------------------------------------


    //가입 페이지이동------------------------------------------------
    @GetMapping("/signuppage")
    public String signupPage(Model model){
        //회원가입시 이메일 중복체크
        List<UserResDTO> userList = userService.showUser();
        model.addAttribute("userList", userList);

        return "/account/signup-test";
    }


    //회원가입처리-----------------------------------------------------------------
    @PostMapping("/signuptest")
    public String signupTest(@Valid UserReqDTO userReqDTO, BindingResult result, Model model){
        //입력 항목 검증 시 오류발생 -> 다시 회원가입 페이지로
        if(result.hasErrors()){
            //검증오류 메세지 alert 띄우기
            model.addAttribute("errorMessage1", "가입 실패 : 올바르지 않은 값 삽입\nex)이메일, 생년월일, 전화번호 양식");
            return "/account/signup-test";
        }
        boolean isSignup = userService.userSignup(userReqDTO);
        if(isSignup){
            //가입성공 메세지 띄우기 alert
            model.addAttribute("successMessage", "가입 완료!");
            System.out.println("sucess!!!!!!");
            return "/account/login-test";
        }else{
            //동작 실패
            //가입 실패 메세지 띄욱 alert
            model.addAttribute("errorMessage2", "가입 실패 : 사용중인 이메일.");
            System.out.println("false!!!!!!");
            return "/account/signup-test";
        }

    }

    //로그인페이지 이동--------------------------------------------------------
    @GetMapping("/loginpage")
    public String loginPage(UserReqDTO userReqDTO){
        return "/account/login-test";
    }

    //로그인 처리----------------------------------------------------------------
    @PostMapping("/login")
    public String userLogin(UserReqDTO userReqDTO, Model model, HttpServletRequest request){
        //로그인 성공 시 true 실패 시 false
        boolean isLogin = userService.userLogin(userReqDTO);

        Long userId = Arrays.stream(request.getCookies())
                .filter(cookie -> "loginUserId".equals(cookie.getName())) // 원하는 쿠키 찾기
                .map(cookie -> Long.parseLong(cookie.getValue())) // 쿠키 값(String)을 Long으로 변환
                .findFirst() // 첫 번째 일치하는 쿠키 가져오기
                .orElse(null); // 쿠키를 찾지 못하면 기본값(null) 사용


        if(isLogin){
            //로그인 성공 시 (로그인 성공 alert 띄우기)
            model.addAttribute("loginok","로그인 성공!");
            model.addAttribute("userId", userId);
            System.out.println(isLogin);
            return "redirect:/account/show";
        }
        else{
            //로그인 실패 시 (로그인 실패 alert 띄우기)
            model.addAttribute("loginx","로그인 실패!");
            System.out.println(isLogin);
            return "/account/login-test";
        }
    }

    //테스트코드.(예비 메인페이지)--------------------------------------------------------------------------
    //유저로그인 성공시 목록띄우기
    @GetMapping("/show")
    public ModelAndView userShow(Model model){
        List<UserResDTO> userResDTOList = userService.showUser();
        model.addAttribute("loginok","로그인 성공!");
        System.out.println(userResDTOList);
        return new ModelAndView("account/userList","users",userResDTOList);
    }

    //유저의 로그아웃 (할예정....)0------------------------------
    @GetMapping("/logout")
    public String userLogout(Model model, HttpServletResponse response, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0); // 쿠키를 만료시켜 삭제
                response.addCookie(cookie);
            }
        }
        // 로그아웃 성공 메시지를 JavaScript로 생성하여 반환
        model.addAttribute("logout","로그아웃");

        return "account/login-test";
    }

    //회원탈퇴 페이지로 이동
    @GetMapping("deletepageu/{id}")
    public  ModelAndView userLeavePage(@PathVariable Long id){
        UserResDTO deleteUser = userService.showUserById(id);
        return new ModelAndView("account/accountdel","user",deleteUser);
    }
    //회원탈퇴 처리(예정)-----------------------------------------------------------------------------
    @PostMapping("/deleteu/{id}")
    public String userLeaveTest(@PathVariable Long id){
        //탈퇴처리 (state 값 -> 'd'로)
        userService.userLeaveTest(id);
        //탈퇴회원 객체 반환
        return "redirect:/userMypage";
    }
    //회원탈퇴처리 테스트 컨트롤러
    @GetMapping("deletetest/{id}")
    public ResponseEntity<UserReqDTO> userLeaveTest(@PathVariable Long id,@RequestBody UserReqDTO userReqDTO){
        //탈퇴처리 (state값 ->d로)
        userService.userLeaveTest(id);

        //@컨트롤러에서는 응답객체로!
        return new ResponseEntity<>(userReqDTO, HttpStatus.OK);
    }

    //회원 수정 페이지 이동
    @GetMapping("/updatepageu/{id}")
    public ModelAndView userUpdatePage(@PathVariable Long id){
    UserResDTO updateUser = userService.showUserById(id);
        return new ModelAndView("account/mypageu","user",updateUser);
    }

    //회원 수정처리 테스트
//    @PostMapping("/updateu/{id}")
//    public ResponseEntity<UserReqDTO> userUpdateTest(@PathVariable Long id, @ModelAttribute UserReqDTO userReqDTO){
//        userService.userUpdateTest(id ,userReqDTO);
//
//        return new ResponseEntity<>(userReqDTO, HttpStatus.OK);
//    }

    //실제 업데이트(수정) 이거로 진행중!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @PostMapping("/updateu/{id}")
    public String userUpdateTest(@PathVariable Long id, @ModelAttribute UserReqDTO userReqDTO) {
        userService.userUpdateTest(id ,userReqDTO);
        System.out.println("컨트롤러에서//////////////////////"+userReqDTO);
        return "redirect:/userMypage";
    }



//-------------여기서 부터는 회사회원의 컨트롤러--------------------------------------------------------------------------------


    //회사회원의 회원가입처리----------------------------------------------------------------------
    //회사회원 회원가입 페이지로 이동
    @GetMapping("/signuppagev")
    public String signupPageV(VenderReqDTO venderReqDTO){
        return "account/signupv";
    }

    //회사회원가입처리----------------------------------------------------------------
    @PostMapping("/signuptestv")
    public String signupTestV(@Valid VenderReqDTO venderReqDTO, BindingResult result, Model model){
        if(result.hasErrors()){
            //검증오류 메세지 alert 띄우기(할 예정)
            model.addAttribute("errorMessage1", "가입 실패 : 올바르지 않은 값 삽입\nex)이메일, 생년월일, 전화번호 양식");
            return "/account/signupv";
        }
        boolean isSignup = venderService.venderSignup(venderReqDTO);

        if(isSignup){
            //가입성공 메세지 띄우기 alert
            model.addAttribute("successMessage", "가입 완료!");
            return "/account/loginv";

        }else{
            //가입 실패 메세지 띄우기 alert
            model.addAttribute("errorMessage2", "가입 실패 : 사용중인 이메일.");
            return "/account/signupv";
        }

    }

    //회사회원 로그인페이지 이동--------------------------------------------------------
    @GetMapping("/loginpagev")
    public String loginPage(VenderReqDTO venderReqDTO){
        return "/account/loginv";
    }

    //회사회원 로그인 처리--------------------------------------------------------------------
    @PostMapping("/loginv")
    public String venderLogin(VenderReqDTO venderReqDTO, Model model, HttpServletResponse response) {
        VenderResDTO isLogin = venderService.venderLogin(venderReqDTO);
        System.out.println(venderReqDTO);
        System.out.println(isLogin);
        if(isLogin!=null){
            //로그인 성공시 메인페이지로
            model.addAttribute("loginok","로그인 성공!");
            System.out.println("기업로긴성공");
            String myToken = jwtTokenProvider.createToken(venderReqDTO.getVenderEmail());
            // 쿠키 생성
            Cookie myTokenCookie = new Cookie("myTokenCookie", myToken);
            myTokenCookie.setMaxAge(1800); // 쿠키 유효 시간 설정 (초 단위)
            myTokenCookie.setPath("/"); // 쿠키 경로 설정
            myTokenCookie.setDomain(""); // 쿠키 도메인 설정

            Cookie loginVenderId = new Cookie("loginVenderId", isLogin.getVenderId().toString());
            loginVenderId.setMaxAge(1800);
            loginVenderId.setPath("/") ;
            loginVenderId.setDomain("");


            // 쿠키를 응답에 추가
            response.addCookie(myTokenCookie);
            response.addCookie(loginVenderId);

            System.out.println("쿠키 설정됨: " + myToken);

            // 리다이렉트
            return "redirect:/"; // 리다이렉트할 경로를 지정
        }else{
            //로글인 실패 alert띄우기.!!
            model.addAttribute("loginx","로그인 실패!");
            System.out.println("기업로긴실패");
            return "/account/loginv";
        }
    }


    //로그인 성공 시 페이지이동 처리(예비) ------------------------------------------------------
    @GetMapping("/showv")
    public ModelAndView venderShow(Model model){
        model.addAttribute("loginok","로그인 성공!");
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

    //회사 회원 수정 페이지 이동
//    @GetMapping("/updatepagev")
//    public String venderUpdatePage(){
//        return "account/mypagev";
//    }

    //회원 수정 페이지 이동
    @GetMapping("/updatepagev/{id}")
    public ModelAndView venderUpdatePage(@PathVariable Long id){
        VenderResDTO updateVender = venderService.showVenderById(id);
        return  new ModelAndView("account/mypagev","vender",updateVender);
    }
    //회원 수정처리 테스트
    @PostMapping("/updatev/{id}")
    public String venderUpdateTest(@PathVariable Long id, @ModelAttribute VenderReqDTO venderReqDTO){
        venderService.venderUpdate(id ,venderReqDTO);

        return "redirect:/venderMypage";
    }



}