package com.dogsole.developersite.admin.controller;


import com.dogsole.developersite.account.dto.user.UserReqDTO;
import com.dogsole.developersite.account.dto.user.UserResDTO;
import com.dogsole.developersite.account.dto.vender.VenderReqDTO;
import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.account.dto.vender.VenderTempResDTO;
import com.dogsole.developersite.account.service.user.UserService;
import com.dogsole.developersite.account.service.vender.VenderService;
import com.dogsole.developersite.account.service.vender.VenderTempService;
import com.dogsole.developersite.jobPost.dto.res.JobPostTempResDTO;
import com.dogsole.developersite.jwt.provider.JwtTokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final VenderTempService venderTempService;
    private final VenderService venderService;

    @GetMapping("/show")
    public ModelAndView userShow(Model model, @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                 @RequestParam(name = "size", required = false, defaultValue = "2") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<UserResDTO> userResDTOList = userService.showUser(pageable);
        return new ModelAndView("admin/userList","users",userResDTOList);
    }

    @GetMapping("/showv")
    public ModelAndView venderShow(Model model, @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                   @RequestParam(name = "size", required = false, defaultValue = "2") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<VenderResDTO> venderResDTOList = venderService.showVender(pageable);
        return new ModelAndView("admin/venderList","venders",venderResDTOList);
    }
    @GetMapping("/showTempVender")
    public ModelAndView showTempVender(Model model,@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                       @RequestParam(name = "size", required = false, defaultValue = "2") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<VenderTempResDTO> venderTempResDTOList = venderTempService.showVenderTemp(pageable);
        return new ModelAndView("admin/venderTempList","venders",venderTempResDTOList);
    }



}