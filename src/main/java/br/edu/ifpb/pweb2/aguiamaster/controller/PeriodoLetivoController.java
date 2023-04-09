package br.edu.ifpb.pweb2.aguiamaster.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.aguiamaster.model.PeriodoLetivo;
import br.edu.ifpb.pweb2.aguiamaster.repository.PeriodoLetivoRepository;
import br.edu.ifpb.pweb2.aguiamaster.service.PeriodoLetivoservice;

@Controller
@RequestMapping("/periodo")
public class PeriodoLetivoController {

    @Autowired
    PeriodoLetivoRepository periodoLetivoRepository;

    @Autowired
    PeriodoLetivoservice periodoLetivoService;

    @RequestMapping("/form")
    public ModelAndView getCadastroPeriodo(PeriodoLetivo periodo,ModelAndView mav){
        mav.addObject("titulo","Cadastro de Periodo");
        mav.addObject("periodo",periodo);
        mav.setViewName("periodo/form");
        return mav;

    }
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(@Valid  PeriodoLetivo periodo,
            ModelAndView mav,BindingResult validation,RedirectAttributes attrs){
                if(validation.hasErrors()){
                    mav.setViewName("periodo/form");
                    return mav;
                }
                if (periodo.getId() == null) {
                    attrs.addFlashAttribute("mensagem", "periodo cadastrado com sucesso!");
                    // mav.addObject("titulo","Cadastra");
                    
                   
                } else {

                    attrs.addFlashAttribute("mensagem", "periodo editado com sucesso!");

                }
                periodoLetivoService.savePeriodo(periodo);
                mav.setViewName("redirect:periodo");
                attrs.addFlashAttribute("mensagem", "periodo cadastrado com sucesso!");
                return mav;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listAll(ModelAndView mav){
        mav.setViewName("periodo/list");
        mav.addObject("periodo",periodoLetivoService.getPeriodoLetivos());
        return mav;
    }
    

    
}
