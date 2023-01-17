package br.edu.ifpb.pweb2.aguiamaster.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.aguiamaster.model.Instituicao;
import br.edu.ifpb.pweb2.aguiamaster.repository.InstituicaoRepository;

@Controller
@RequestMapping("/instituicao")
public class InstituicaoController {

        @Autowired
        InstituicaoRepository instituicaoRepository;
        @RequestMapping("/ins-form") 
        public ModelAndView getCasdastroInstituicao(Instituicao instituicao , ModelAndView mav){
            mav.addObject("instituicao",instituicao);
            mav.setViewName("instituicao/ins-form");
            return  mav;
        }
    
        @ModelAttribute("instituicaoItens")
        public List<Instituicao> getInstituicaos() {
            return instituicaoRepository.findAll();
        }
    
        @RequestMapping(method = RequestMethod.POST)
        public ModelAndView save(@Valid  Instituicao instituicao,
                ModelAndView mav,BindingResult validation,RedirectAttributes attrs){
                    if(validation.hasErrors()){
                        mav.setViewName("Instituicao/ins-form");
                        return mav;
                    }
                    mav.setViewName("Instituicao/ins-form");
                   // Instituicao.setInstituicao(Instituicao.getInstituicao());
                    instituicaoRepository.save(instituicao);
                    mav.setViewName("redirect:instituicao");
                    attrs.addFlashAttribute("mensagem", "Estudante cadastrado com sucesso!");
                    return mav;
        }
        @RequestMapping(method = RequestMethod.GET)
        public ModelAndView listAll(ModelAndView mav){
            mav.addObject("instituicoes", instituicaoRepository.findAll());
            mav.setViewName("instituicao/list");
            return mav;
    
        }
}
