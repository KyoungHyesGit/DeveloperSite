package com.dogsole.developersite.jp_apply.controller;

import com.dogsole.developersite.exception.BusinessException;
import com.dogsole.developersite.jp_apply.service.JpApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Transactional
@RequestMapping("/api/jpApply")
public class JpApplyRestController {

    private final JpApplyService jpApplyService;

    @PostMapping("/changeUserState/{id}")
    public ResponseEntity<Map<String, String>> changeUserState(@PathVariable Long id, @RequestBody String userState) {
        Map<String, String> response = new HashMap<>();
        try {
            String chageState = jpApplyService.changeUserState(id, userState);
            response.put("chageState", chageState);
            response.put("message", "success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BusinessException ex) {
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
