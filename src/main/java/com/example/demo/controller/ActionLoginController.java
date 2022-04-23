package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class ActionLoginController {
    @GetMapping("user")
    public String login(){
        return "user";
    }
    @GetMapping("admin")
    public String login1(){
        return "admin";
    }

}
