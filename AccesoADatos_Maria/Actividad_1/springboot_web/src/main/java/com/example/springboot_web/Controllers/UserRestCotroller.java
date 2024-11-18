package com.example.springboot_web.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class UserRestCotroller {
    
    @RestController
public class userController {
        

    
    @GetMapping("/details")
    public Map<Integer,HashMap> detailMap(){
        Map<Integer,HashMap> body = new HashMap<>();
        HashMap<String,String> usu = new HashMap();
      

        for (int i = 0; i < 4; i++) {
            
            
            usu.put("title", "Hola Mundo"+1);
            usu.put("name", "Paco");
            usu.put("lastname", "Rest");

            body.put(i, usu);
        }

        

        return body;
    }


}
}
