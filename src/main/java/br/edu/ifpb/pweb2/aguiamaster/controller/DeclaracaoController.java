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


import br.edu.ifpb.pweb2.aguiamaster.model.Declaracao;
import br.edu.ifpb.pweb2.aguiamaster.model.Estudante;
import br.edu.ifpb.pweb2.aguiamaster.service.DeclaracaoService;
import br.edu.ifpb.pweb2.aguiamaster.service.EstudanteService;

@Controller
@RequestMapping("/declaracao")
public class DeclaracaoController {
    @Autowired
    DeclaracaoService declaracaoService;

    @Autowired
    EstudanteService estudanteService;

    @RequestMapping("/form")
    public ModelAndView getCadastroDecaracao(Declaracao declaracao ,ModelAndView mav){
        mav.addObject("declaracao", declaracao);
        mav.setViewName("declaracao/form");
        mav.addObject("titulo","Cadastro Declaração");
        return mav;

    }

    @ModelAttribute("estudantes")
    public List<Estudante> getEstudantes() {
        return estudanteService.getEstudantes();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(@Valid Declaracao declaracao, ModelAndView mav, BindingResult validation, RedirectAttributes attrs){
        if(validation.hasErrors()){
            mav.setViewName("declaracao/form");
        }
        if(declaracao.getId()==null){
            attrs.addFlashAttribute("mensagem","cadastrado com sucesso!");
        }
        declaracaoService.salveDecaracao(declaracao);
        mav.setViewName("redirect:declaracao");
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listAll(ModelAndView mav) {
        mav.setViewName("declaracao/list");
        mav.addObject("declaracoes", declaracaoService.getDeclaracao());
        return mav;

    }

    
}
