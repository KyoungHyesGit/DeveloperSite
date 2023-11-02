package com.dogsole.developersite.chat.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    private String filePath = "C:\\ucamp\\project\\DeveloperSite\\src\\main\\java\\com\\dogsole\\developersite\\chat\\chat.txt"; // 파일 경로를 설정합니다.

    public List<String> getChatHistory() {
        List<String> chatHistory = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                chatHistory.add(line);
            }
        } catch (IOException e) {
            // 파일 읽기 오류 처리
            e.printStackTrace();
        }
        return chatHistory;
    }
}
