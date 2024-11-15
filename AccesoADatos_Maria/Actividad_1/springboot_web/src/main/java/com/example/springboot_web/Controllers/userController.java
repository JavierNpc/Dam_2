package com.example.springboot_web.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class userController {
        

    
    @GetMapping("/details")
    public Map<Integer,HashMap> detailMap(){
        Map<Integer,HashMap> body = new HashMap<>();
        HashMap<String,String> usu = new HashMap();
        ArrayList<String> list = new ArrayList();

        for (int i = 0; i < 4; i++) {
            
            
            usu.put("title", "Hola Mundo");
            usu.put("name", "Paco");
            usu.put("lastname", "Rest");

            body.put(i, usu);
        }

        
       
    
        return body;
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


@GetMapping("/details")
    public Map<Integer,HashMap> detailMap(){
        Map<Integer,HashMap> body = new HashMap<>();
    
        HashMap<String,Object> usu = new HashMap<>();
        for(int i = 0 ; i<= 3 ; i++){
            usu.put("name", "Javi"+i);
            body.put(i,  usu);
        }
       
        return body;
    }
         */