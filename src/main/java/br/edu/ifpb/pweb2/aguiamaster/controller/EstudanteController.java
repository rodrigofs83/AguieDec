package br.edu.ifpb.pweb2.aguiamaster.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.aguiamaster.model.Estudante;
import br.edu.ifpb.pweb2.aguiamaster.model.Instituicao;
import br.edu.ifpb.pweb2.aguiamaster.repository.InstituicaoRepository;
import br.edu.ifpb.pweb2.aguiamaster.service.EstudanteService;

@Controller
@RequestMapping("/estudante")
public class EstudanteController {
    @Autowired
    EstudanteService estudanteService;
    @Autowired
    InstituicaoRepository instituicaoRepository;
    @RequestMapping("/form") 
    public ModelAndView getCasdastroEstudante(Estudante estudante , ModelAndView mav){
        mav.addObject("estudante",estudante);
        mav.setViewName("estudante/form");
        return  mav;
    }

    @ModelAttribute("instituicaoItens")
    public List<Instituicao> getInstituicaos() {
        return instituicaoRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(@Valid Estudante estudante,
            ModelAndView mav,BindingResult validation,RedirectAttributes attrs){
                if(validation.hasErrors()){
                    mav.setViewName("estudante/form");
                    return mav;
                }
                mav.setViewName("estudante/form");
                estudanteService.saveEstudate(estudante);
                mav.setViewName("redirect:estudante");
                attrs.addFlashAttribute("mensagem", "Estudante cadastrado com sucesso!");
                return mav;
    }
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listAll(ModelAndView mav){
        mav.addObject("estudantes", estudanteService.getEstudantes());
        mav.setViewName("estudante/list");
        return mav;

    }

    @RequestMapping("/{id}")
    public ModelAndView getEstudanteById(@PathVariable(value = "id") Integer id, ModelAndView mav) {
     Estudante estudante = null;
     /*
     ofNullable​ - Se um valor estiver presente, 
     retorna um Optional com o valor , caso contrário,
      retorna um Optional vazio. Este é um dos métodos mais indicados 
      para criar um Optional.
       */
        Optional<Estudante> opestudante = Optional.ofNullable(estudanteService.getEstudanteById(id));
         if (opestudante.isPresent()) {
            estudante = opestudante.get();
            mav.addObject("estudante", estudante);
            mav.setViewName("estudantes/form");
        } else {
            mav.addObject("mensagem", "estudante com id=" + id + " não encontrado!");
            mav.setViewName("estudante/list");
        }
        return mav;
    }
    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable("id") Integer id, ModelAndView mav, RedirectAttributes attr) {
        
        estudanteService.deleteEstudanteById(id);
        attr.addFlashAttribute("mensagem", "Estudante removido com sucesso!");
        mav.setViewName("redirect:/list");
        return mav;
    }
}  
