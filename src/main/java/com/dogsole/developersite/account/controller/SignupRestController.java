package com.dogsole.developersite.account.controller;

import com.dogsole.developersite.account.dto.user.UserResDTO;
import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.account.service.user.UserService;
import com.dogsole.developersite.account.service.vender.VenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class SignupRestController {

    private final UserService userService;
    private final VenderService venderService;

    //이메일 중복 처리버튼------------------------------------------------------
    @GetMapping("/checkEmail")
    public ResponseEntity<String> checkEmail(@RequestParam String email){
        System.out.println(email);
        UserResDTO user = null;
        VenderResDTO vender = null;
        try {
            user = userService.showUserByEmail(email);
        }catch (Exception e){

        }
        System.out.println(user);

        try {
           vender = venderService.showVenderByEmail(email);
        }catch (Exception e){

        }
        System.out.println(vender);

        if((user != null) || (vender != null)){
            return ResponseEntity.ok("(X)이미 사용중인 이메일입니다.");
        }else{
            return ResponseEntity.ok("(O)사용 가능한 이메일입니다!");
        }
    }
}
