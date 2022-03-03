package com.haimp02.pointofsales.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {
    

    @RequestMapping("/")
    String index() {
        return "index";
    }

}
