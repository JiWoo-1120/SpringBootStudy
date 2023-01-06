package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FristController {

    @GetMapping("/hi")
    public String niceToMeetYou() {
        return "greetings";    // templates/greetings.mustache -> 브라우저 전송
    }

}
