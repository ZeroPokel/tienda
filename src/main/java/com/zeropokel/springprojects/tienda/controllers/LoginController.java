package com.zeropokel.springprojects.tienda.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    
    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @GetMapping(value = {"/welcome"})
    public String welcome(){

        return "welcome";
    }

}
