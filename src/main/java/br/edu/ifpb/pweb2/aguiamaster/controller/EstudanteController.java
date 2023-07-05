package br.edu.ifpb.pweb2.aguiamaster.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.aguiamaster.model.Declaracao;
import br.edu.ifpb.pweb2.aguiamaster.model.Estudante;
import br.edu.ifpb.pweb2.aguiamaster.model.Instituicao;
import br.edu.ifpb.pweb2.aguiamaster.model.Usuario;
import br.edu.ifpb.pweb2.aguiamaster.repository.UserRepository;
import br.edu.ifpb.pweb2.aguiamaster.service.EstudanteService;
import br.edu.ifpb.pweb2.aguiamaster.service.InstituicaoService;

@Controller
@RequestMapping("/estudante")
public class EstudanteController {

    @Autowired
    EstudanteService estudanteService;

    @Autowired
    InstituicaoService instituicaoService;

    @Autowired
    UserRepository userRepository;


    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE')")
    @RequestMapping("/form")
    public ModelAndView getCasdastroEstudante(Estudante estudante, ModelAndView mav) {
        mav.addObject("estudante", estudante);
        mav.setViewName("estudante/form");
        mav.addObject("titulo", "Cadastro  de Estudante ");
        return mav;
    }

    @ModelAttribute("users")
    public List<Usuario> getUsersOptions(){
        return userRepository.findByEnabledTrue();
    }
    @ModelAttribute("instituicaoItens")
    public List<Instituicao> getInstituicaos() {
        return instituicaoService.getInstituicao();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(@Valid Estudante estudante,BindingResult validation,
            ModelAndView mav, RedirectAttributes attrs) {
        if (validation.hasErrors()) {
            mav.addObject("message", "Erros de validação! Corrija-os e tente novamente.");
            mav.setViewName("estudante/form");
            return mav;
        }
        if (estudante.getId() == null) {
            attrs.addFlashAttribute("mensagem", "Estudante cadastrado com sucesso!");
            // mav.addObject("titulo","Cadastra");

        } else {

            attrs.addFlashAttribute("mensagem", "Estudante editado com sucesso!");

        }
        estudanteService.saveEstudate(estudante);
        mav.setViewName("redirect:estudante");
        return mav;

    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE')")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listAll(ModelAndView mav) {
        mav.setViewName("estudante/list");
        mav.addObject("estudantes", estudanteService.getEstudantes());
        return mav;

    }

    @RequestMapping("/{id}")
    public ModelAndView getEstudanteById(@PathVariable(value = "id") Integer id, ModelAndView mav) {
        Estudante estudante = null;
        /*
         * ofNullable​ - Se um valor estiver presente,
         * retorna um Optional com o valor , caso contrário,
         * retorna um Optional vazio. Este é um dos métodos mais indicados
         * para criar um Optional.
         */
        Optional<Estudante> opestudante = Optional.ofNullable(estudanteService.getEstudanteById(id));
        if (opestudante.isPresent()) {
            estudante = opestudante.get();
            mav.addObject("estudante", estudante);
            mav.setViewName("estudantes/form");
        } else {
            mav.addObject("mensagem", "estudante com id=" + id + " não encontrado!");
            mav.setViewName("estudante");
        }
        return mav;
    }

    @RequestMapping("/excluir/{id}")
    public ModelAndView deleteById(@PathVariable("id") Integer id, ModelAndView mav, RedirectAttributes attr) {

        estudanteService.deleteEstudanteById(id);
        attr.addFlashAttribute("mensagem", "Estudante removido com sucesso!");
        mav.setViewName("redirect:/estudante");
        return mav;
    }

    @RequestMapping("/edita/{id}")
    public ModelAndView edita(@PathVariable("id") Integer id, ModelAndView mav, RedirectAttributes attr) {
        // estudanteService.editaEstudanteById(id);
        Estudante e = estudanteService.getEstudanteById(id);
        mav.addObject("estudante", e);
        mav.addObject("titulo", "Editando");
        // attr.addFlashAttribute("mensagem", "Estudante editado com sucesso!");
        mav.setViewName("estudante/form");
        return mav;
    }
    @RequestMapping("/enviar/{id}")
    public ModelAndView enviaDeclaracao(@PathVariable("id") Integer id, ModelAndView mav) {
            Estudante estudante = estudanteService.getEstudanteById(id);
                Declaracao declaracao = new Declaracao();
                mav.addObject("estudante", estudante);
                mav.addObject("declaracao", declaracao);
                mav.setViewName("declaracao/form");
            
    
            
            return mav;
    }
}
