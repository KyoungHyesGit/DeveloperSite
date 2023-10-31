package com.dogsole.developersite.account.controller;

import com.dogsole.developersite.account.dto.vender.VenderReqDTO;
import com.dogsole.developersite.account.dto.vender.VenderTempReqDTO;
import com.dogsole.developersite.account.dto.vender.VenderTempResDTO;
import com.dogsole.developersite.account.service.vender.VenderTempService;
import com.dogsole.developersite.common.dto.res.LovResDTO;
import com.dogsole.developersite.common.service.LovService;
import com.dogsole.developersite.jobPost.dto.req.JobPostTempReqDTO;
import com.dogsole.developersite.jobPost.dto.req.JobPostTempReqFormDTO;
import com.dogsole.developersite.jobPost.dto.res.JobPostTempResDTO;
import com.dogsole.developersite.jobPost.service.JobPostTempService;
import com.dogsole.developersite.userResume.entity.UserResumeEntity;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Transactional
@RequestMapping("/vender")
public class VenderTempController {

    private final VenderTempService venderTempService;

    @GetMapping("/add")
    public String gotoAddJobPostTempPage(VenderTempReqDTO venderTempReqDTO, Model model){
        return "/account/add_vender_temp";
    }

    @PostMapping("/add")
    public String addJobPostTemp(@ModelAttribute @Valid VenderTempReqDTO venderTempReqDTO, @RequestParam("photo") MultipartFile photo, BindingResult result, Model model, HttpServletRequest request){
        if (result.hasErrors()) {
            return "/account/add_vender_temp";
        }
        try{
            Cookie[] cookies = request.getCookies();
            Long userId = Arrays.stream(request.getCookies())
                    .filter(cookie -> "loginUserId".equals(cookie.getName())) // 원하는 쿠키 찾기
                    .map(cookie -> Long.parseLong(cookie.getValue())) // 쿠키 값(String)을 Long으로 변환
                    .findFirst() // 첫 번째 일치하는 쿠키 가져오기
                    .orElse(null); // 쿠키를 찾지 못하면 기본값(null) 사용
            venderTempService.createVenderTemp(venderTempReqDTO,photo,userId);
        }catch (Exception e){

        }
        return "redirect:/userMypage";
    }

    @GetMapping("/edit/{id}")
    public String gotoEditJobPostTempPage(@PathVariable Long id, Model model){
        VenderTempResDTO venderTemp = venderTempService.findById(id);

        model.addAttribute("venderTemp",venderTemp);

        return "/account/edit_vender_temp";
    }

    @PostMapping("/edit/{id}")
    public String addJobPostTemp(@ModelAttribute("venderTemp") @Valid VenderTempReqDTO venderTemp, @RequestParam("photo") MultipartFile photo, BindingResult result, Model model, HttpServletRequest request, @PathVariable Long id){
        if(result.hasErrors()){

            model.addAttribute("venderTemp",venderTemp);
            return "/account/edit_vender_temp";
        }
        venderTemp.setState("수정");
        venderTemp.setReqState("");

        try{
            venderTempService.updateVenderTemp(id, venderTemp, photo);
        }catch (Exception e){}

        return "redirect:/userMypage";
    }

    @GetMapping("/delete/{id}")
    public String deleteJobPostTemp(@PathVariable Long id) {
        venderTempService.deleteVenderTemp(id);
        // TODO 사용자 정보에서 벤터 id빼서 홈으로 가기
        return "redirect:/userMypage";
    }

}
