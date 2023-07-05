package br.edu.ifpb.pweb2.aguiamaster.controller;

import java.time.LocalDate;
import java.util.List;

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

import br.edu.ifpb.pweb2.aguiamaster.model.Instituicao;
import br.edu.ifpb.pweb2.aguiamaster.model.PeriodoLetivo;
import br.edu.ifpb.pweb2.aguiamaster.repository.PeriodoLetivoRepository;
import br.edu.ifpb.pweb2.aguiamaster.service.InstituicaoService;
import br.edu.ifpb.pweb2.aguiamaster.service.PeriodoLetivoService;

@Controller
@RequestMapping("/periodo")
public class PeriodoLetivoController {

    @Autowired
    PeriodoLetivoRepository periodoLetivoRepository;

    @Autowired
    PeriodoLetivoService periodoLetivoService;

    @Autowired
    InstituicaoService instituicaoService;

    @RequestMapping("/form")
    public ModelAndView getCadastroPeriodo(PeriodoLetivo periodo, ModelAndView mav) {
        mav.addObject("titulo", "Cadastro de Periodo");
        mav.addObject("periodo", periodo);
        mav.setViewName("periodo/form");
        return mav;

    }

    @ModelAttribute("instituicaoItens")
    public List<Instituicao> getInstituicaos() {
        return instituicaoService.getInstituicao();
    }

    @ModelAttribute("hoje")
    public int getDataAtual(){
        LocalDate data = LocalDate.now();
        int hoje = data.getYear();
        
        return hoje;
    }
    

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(@Valid PeriodoLetivo periodo, BindingResult validation,
            ModelAndView mav, RedirectAttributes attrs) {
        if (validation.hasErrors()) {
            mav.setViewName("periodo/form");
            return mav;
        }
        
        if (periodo.getId() == null) {
            attrs.addFlashAttribute("mensagem", "periodo cadastrado com sucesso!");
            // mav.addObject("titulo","Cadastra");

        } else {

            attrs.addFlashAttribute("mensagem", "periodo editado com sucesso!");

        }
        Instituicao instituicao = null;
        if(periodo.getInstituicao() != null){
            instituicao = instituicaoService.getInstituicaoById(periodo.getInstituicao().getId());
            instituicao.setPeriodoAtual(periodo);
        }

        periodoLetivoService.savePeriodo(periodo);
        mav.setViewName("redirect:periodo");
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listAll(ModelAndView mav) {
        mav.setViewName("periodo/list");
        mav.addObject("periodos", periodoLetivoService.getPeriodoLetivos());
        return mav;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/excluir/{id}")
    public ModelAndView deletePeriodoById(@PathVariable(value = "id") Integer id, ModelAndView mav,
            RedirectAttributes attr) {
        periodoLetivoService.deletePeriodoLetivoById(id);
        attr.addFlashAttribute("messagem", " removida com susseso!");
        mav.setViewName("redirect:/periodo");
        return mav;

    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/edita/{id}")
    public ModelAndView editaPeriodoLetivoById(@PathVariable(value = "id") Integer id, ModelAndView mav,
            RedirectAttributes attr) {
        PeriodoLetivo peLetivo = periodoLetivoService.getPeriodoLetivoById(id);
        mav.addObject("periodo", peLetivo);
        mav.addObject("titulo", "Editar");
        mav.setViewName("periodo/form");
        return mav;
    }

}
