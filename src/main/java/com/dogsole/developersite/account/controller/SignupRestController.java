package com.dogsole.developersite.account.controller;

import com.dogsole.developersite.account.dto.user.UserResDTO;
import com.dogsole.developersite.account.dto.vender.VenderResDTO;
import com.dogsole.developersite.account.service.user.UserService;
import com.dogsole.developersite.account.service.vender.VenderService;

import com.dogsole.developersite.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
            String xHtml = "(X) 이미 사용중인 이메일입니다.";
            return ResponseEntity.ok().body(xHtml);
        }else if (email.isEmpty()){
            String xHtml = "(X) 이메일을 입력해주세요.";
            return ResponseEntity.ok().body(xHtml);
        }else{
            String okHtml = "(O) 사용 가능한 이메일입니다!";
            return ResponseEntity.ok().body(okHtml);
        }
    }

    @GetMapping("/readysendnum")
    public boolean readySendNum(@RequestParam String email){
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
        if((user != null) || (vender != null) ||email.isEmpty()){

            return false;
        }else{

            return true;
        }
    }

    @PostMapping("/block/{id}")
    public ResponseEntity<Map<String, String>> blockUser(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            userService.userBlock(id);
            response.put("message", "success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BusinessException ex) {
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
