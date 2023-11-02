package com.dogsole.developersite.common.controllerAdvice;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("allCookies")
    public Map<String, String> addAllCookiesToModel(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Map<String, String> cookieMap = new HashMap<>();
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        
        return cookieMap;
    }
}