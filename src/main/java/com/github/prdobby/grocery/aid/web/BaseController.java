package com.github.prdobby.grocery.aid.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String getHomePage(final Model model) {
        model.addAttribute("appName", appName);
        return "index";
    }
}

