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

import br.edu.ifpb.pweb2.aguiamaster.model.Instituicao;
import br.edu.ifpb.pweb2.aguiamaster.model.PeriodoLetivo;
import br.edu.ifpb.pweb2.aguiamaster.repository.InstituicaoRepository;
import br.edu.ifpb.pweb2.aguiamaster.service.InstituicaoService;
import br.edu.ifpb.pweb2.aguiamaster.service.PeriodoLetivoService;

@Controller
@RequestMapping("/instituicao")
public class InstituicaoController {

    @Autowired
    InstituicaoRepository instituicaoRepository;

    @Autowired
    InstituicaoService instituicaoService;

    @Autowired
    PeriodoLetivoService periodoLetivoService;
    @RequestMapping("/form")
    public ModelAndView getCasdastroInstituicao(Instituicao instituicao, ModelAndView mav) {
        mav.addObject("titulo", "Cadastro de Instituição");
        mav.addObject("instituicao", instituicao);
        mav.setViewName("instituicao/form");
        return mav;
    }

    @ModelAttribute("instituicaoItens")
    public List<Instituicao> getInstituicaos() {
        return instituicaoService.getInstituicao();
    }

    @ModelAttribute("periodosCadastrados")
    public List<PeriodoLetivo> getPeriodos() {
        return periodoLetivoService.getPeriodoLetivos();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(@Valid Instituicao instituicao,
            ModelAndView mav, BindingResult validation, RedirectAttributes attrs) {
        if (validation.hasErrors()) {
            mav.setViewName("Instituicao/form");
            return mav;
        }
        if (instituicao.getId() == null) {
            attrs.addFlashAttribute("mensagem", "Instituição cadastrado com sucesso!");
            // mav.addObject("titulo","Cadastra");

        } else {

            attrs.addFlashAttribute("mensagem", "Instituição editado com sucesso!");

        }
        instituicaoService.saveInstituicao(instituicao);
        mav.setViewName("redirect:instituicao");
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listAll(ModelAndView mav) {
        mav.addObject("instituicoes", instituicaoService.getInstituicao());
        mav.setViewName("instituicao/list");
        return mav;

    }

    @RequestMapping("/{id}")
    public ModelAndView getInstituicaoById(@PathVariable(value = "id") Integer id, ModelAndView mav) {
        Instituicao instituicao = null;
        Optional<Instituicao> opInst = Optional.ofNullable(instituicaoService.getInstituicaoById(id));
        if (opInst.isPresent()) {
            mav.addObject("instituicao", instituicao);
            mav.setViewName("instituicao/form");
        } else {
            mav.addObject("mensagem", "instituicao com id=" + id + " não encontrado!");
            mav.setViewName("instituicao");

        }
        return mav;
    }

    @RequestMapping("/excluir/{id}")
    public ModelAndView deleteInstituicaoById(@PathVariable("id") Integer id, ModelAndView mav,RedirectAttributes attr) {

        instituicaoService.deleteInstituicaoById(id);
        attr.addFlashAttribute("mensagem", "instrituição removida com susseso!");
        mav.setViewName("redirect:/instituicao");
        return mav;

    }

    @RequestMapping("/edita/{id}")
    public ModelAndView editaInstituicaoById(@PathVariable(value = "id") Integer id, ModelAndView mav,
            RedirectAttributes attr) {
        Instituicao ins = instituicaoService.getInstituicaoById(id);
        mav.addObject("instituicao", ins);
        mav.addObject("titulo", "Editar");
        mav.setViewName("instituicao/form");
        return mav;
    }

}
