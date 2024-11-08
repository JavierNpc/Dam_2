package com.example.springboot_web.Controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class usercontroller {
        
    @GetMapping("/llamada")
    public String mostrar(){
        return "details";
    }

}
