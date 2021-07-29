package com.mingg.minggblog.web;

import com.mingg.minggblog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class indexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }
    @GetMapping("/type")
    public String type(){
        return "types";
    }
    @GetMapping("/tag")
    public String tag(){
        return "tags";
    }
    @GetMapping("/archives")
    public String archives(){
        return "archives";
    }
}
