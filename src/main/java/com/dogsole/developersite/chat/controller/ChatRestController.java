package com.dogsole.developersite.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@RestController
public class ChatRestController {
    private String filePath = "C:\\ucamp\\project\\DeveloperSite\\src\\main\\java\\com\\dogsole\\developersite\\chat\\chat.txt"; // 파일 경로를 설정합니다.

//    @PostMapping("/send-chat")
//    public ResponseEntity<String> sendChatMessage(@RequestBody ChatMessage chatMessage) {
//        try {
//            // 메시지를 파일에 추가
//            Files.write(Paths.get(filePath), (chatMessage.getMessage() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
//
//            return new ResponseEntity<>("Message sent successfully.", HttpStatus.OK);
//        } catch (IOException e) {
//            return new ResponseEntity<>("Failed to send message.", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
