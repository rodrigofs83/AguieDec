package br.edu.ifpb.pweb2.aguiamaster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomerController {
    @RequestMapping
    public String getHomer(){
        return "index";
    }
    
}
