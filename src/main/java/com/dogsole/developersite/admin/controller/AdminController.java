package com.dogsole.developersite.admin.controller;


import com.dogsole.developersite.account.dto.user.UserReqDTO;
import com.dogsole.developersite.account.dto.user.UserResDTO;
import com.dogsole.developersite.account.dto.vender.VenderReqDTO;
import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.account.service.user.UserService;
import com.dogsole.developersite.account.service.vender.VenderService;
import com.dogsole.developersite.jwt.provider.JwtTokenProvider;
import jakarta.servlet.http.Cookie;
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

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final VenderService venderService;

    @GetMapping("/show")
    public ModelAndView userShow(Model model){
        List<UserResDTO> userResDTOList = userService.showUser();
        return new ModelAndView("admin/userList","users",userResDTOList);
    }

    @GetMapping("/showv")
    public ModelAndView venderShow(Model model){
        List<VenderResDTO> venderResDTOList = venderService.showVender();
        return new ModelAndView("admin/venderList","venders",venderResDTOList);
    }



}