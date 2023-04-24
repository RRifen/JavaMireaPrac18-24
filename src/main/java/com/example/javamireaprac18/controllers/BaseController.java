package com.example.javamireaprac18.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }


    @GetMapping("/admin")
    public String admin() {return "admin";}

}
