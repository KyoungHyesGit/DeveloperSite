package com.dogsole.developersite.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PageController {
    @GetMapping("/userMypage")
    public String userMypage(){

        return "userMypage";
    }
}
