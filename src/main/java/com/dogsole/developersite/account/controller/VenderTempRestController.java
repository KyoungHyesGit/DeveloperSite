package com.dogsole.developersite.account.controller;

import com.dogsole.developersite.account.dto.user.UserResDTO;
import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.account.dto.vender.VenderTempReqDTO;
import com.dogsole.developersite.account.service.vender.VenderTempService;
import com.dogsole.developersite.jobPost.dto.req.JobPostTempReqFormDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Transactional
@RequestMapping("/api/vender")
public class VenderTempRestController {

    private final VenderTempService venderTempService;

    @GetMapping("/checkVenderName")
    public ResponseEntity<String> checkVenderName(@RequestParam("name") String name){

        boolean isExist = venderTempService.isExistName(name);

        if(isExist){
            return ResponseEntity.ok("이미 사용중인 이름입니다.");
        }else{
            return ResponseEntity.ok("사용 가능한!");
        }

    }

}
