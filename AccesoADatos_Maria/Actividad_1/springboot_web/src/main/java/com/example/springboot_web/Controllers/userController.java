package com.example.springboot_web.Controllers;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_web.Models.User;


@Controller
public class userController {

    /* 
        @GetMapping("/llamada")
        public String mostrar(Model model){
            model.addAttribute("title", "Hola mundo");
            model.addAttribute("name", "Javi");
            model.addAttribute("lastname", "Maceda");
            return "details";
        }
    */
        
    @GetMapping("/models")
    public String mostrar(Map<String,Object> model){
       
      User user = new User("Mjose", "Martin");

        model.put("title", "hola mundo");
        model.put("user", user);
        
       

       
    
        return "details";
    }

}

/* 

    @GetMapping("/llamada")
    public String mostrar(Model model){
        model.addAttribute("title", "Hola mundo");
        model.addAttribute("name", "Javi");
        model.addAttribute("lastname", "Maceda");
        return "details";
    }


*/