package com.mingg.minggblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutShowController {

    @GetMapping("/about")
    public String archives(Model model) {
        return "about";
    }
}
