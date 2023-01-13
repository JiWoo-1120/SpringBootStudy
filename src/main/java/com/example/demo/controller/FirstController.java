package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username","jiwoo");
        return "greetings";    // templates/greetings.mustache -> 브라우저 전송
    }

    @GetMapping("/bye")
    public String goodBye(Model model) {
        model.addAttribute("username", "지우");
        return "goodbye";

    }

}
