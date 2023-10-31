package com.dogsole.developersite.mail.controller;

import com.dogsole.developersite.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping("/tomail")
    public String toMail() {
        return "mail";
    }

    @PostMapping("/mail")
    @ResponseBody
    public ResponseEntity<Map<String, String>> MailSend(String mail) {
        Map<String, String> response = new HashMap<>();
        if (mail == null || mail.isEmpty()) {
            response.put("message", "이메일 주소가 유효하지 않습니다.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        int number = mailService.sendMail(mail);
        if (number > 0) {
            response.put("message", "이메일 전송에 성공했습니다.");
            response.put("confirmationNumber", String.valueOf(number));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "이메일 전송에 실패했습니다.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}