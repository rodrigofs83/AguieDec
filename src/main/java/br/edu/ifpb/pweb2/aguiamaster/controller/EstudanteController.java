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
               // estudante.setInstituicao(estudante.getInstituicao());
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
       // Correntista correntista = null;
        //Optional<Correntista> opCorrentista = correntistaRepository.findById(id);
       /*   if (opCorrentista.isPresent()) {
            correntista = opCorrentista.get();
            mav.addObject("correntista", correntista);
            mav.setViewName("correntistas/form");
        } else {
            mav.addObject("mensagem", "Correntista com id=" + id + " não encontrado!");
            mav.setViewName("contas/list");
        }*/
        return mav;
    }
}  
