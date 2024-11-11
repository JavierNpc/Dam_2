package com.example.springboot_web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class userController {
        
    @GetMapping("/llamada")
    public String mostrar(Model model){
        model.addAttribute("title", "Hola mundo");
        model.addAttribute("name", "Javi");
        model.addAttribute("lastname", "Maceda");
        return "details";
    }

}
