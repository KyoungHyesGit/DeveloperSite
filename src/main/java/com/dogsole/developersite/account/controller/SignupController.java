package com.dogsole.developersite.account.controller;


import com.dogsole.developersite.account.dto.user.UserReqDTO;
import com.dogsole.developersite.account.dto.user.UserResDTO;
import com.dogsole.developersite.account.dto.vender.VenderReqDTO;
import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.account.entity.user.UserEntity;
import com.dogsole.developersite.account.service.user.UserService;
import com.dogsole.developersite.account.service.vender.VenderService;
import jakarta.servlet.http.HttpServletRequest;
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
    public String signupTest(@Valid UserReqDTO userReqDTO, BindingResult result,  Model model){
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
            model.addAttribute("userId", "userId");
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
    @PostMapping("/logout")
    public String userLogout(Model model){
        //로그아웃 완료 alert창
        model.addAttribute("logout","로그아웃 되었습니다."); //이거지금 안됨
        return "account/login-test";
    }

    //회원탈퇴 처리 (예정)-----------------------------------------------------------------------------
    @GetMapping("/delete/{id}")
    public UserReqDTO userLeave(UserReqDTO userReqDTO, String id){
        //탈퇴처리 (state 값 -> 'd'로)
        userService.userLeave(userReqDTO.getUserEmail());
        //탈퇴회원 객체 반환
        return userReqDTO;
    }
    //회원탈퇴처리 테스트 컨트롤러
    @GetMapping("deletetest/{id}")
    public ResponseEntity<UserReqDTO> userLeaveTest(@PathVariable Long id,@RequestBody UserReqDTO userReqDTO){
        //탈퇴처리 (state값 ->d로)
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
    public String venderLogin(VenderReqDTO venderReqDTO, Model model){
        boolean isLogin = venderService.venderLogin(venderReqDTO);
        System.out.println(venderReqDTO);
        System.out.println(isLogin);
        if(isLogin){
            //로그인 성공시 메인페이지로
            model.addAttribute("loginok","로그인 성공!");
            System.out.println("기업로긴성공");
            return "redirect:/account/showv";

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


}