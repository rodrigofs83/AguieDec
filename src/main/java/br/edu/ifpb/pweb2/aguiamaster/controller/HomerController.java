package br.edu.ifpb.pweb2.aguiamaster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

@RequestMapping("/home")
public class HomerController {

    @GetMapping
    public ModelAndView getHomer(ModelAndView mav) {
        mav.setViewName("home/home");
        return mav;
    }

}
