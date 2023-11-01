package com.dogsole.developersite.chat.controller;

import com.dogsole.developersite.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @RequestMapping("/chat")
    public String chat(Model model) {
        List<String> chatHistory = chatService.getChatHistory();
        model.addAttribute("chatHistory", chatHistory);
        return "userMypage"; // chat.html 또는 웹 페이지 템플릿의 이름
    }
}
