package br.edu.ifpb.pweb2.aguiamaster.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifpb.pweb2.aguiamaster.model.Declaracao;
import br.edu.ifpb.pweb2.aguiamaster.model.Documento;
import br.edu.ifpb.pweb2.aguiamaster.model.Estudante;
import br.edu.ifpb.pweb2.aguiamaster.model.PeriodoLetivo;
import br.edu.ifpb.pweb2.aguiamaster.repository.DeclaracaoRepository;
import br.edu.ifpb.pweb2.aguiamaster.service.DeclaracaoService;
import br.edu.ifpb.pweb2.aguiamaster.service.DocumentoService;
import br.edu.ifpb.pweb2.aguiamaster.service.EstudanteService;
import br.edu.ifpb.pweb2.aguiamaster.service.InstituicaoService;
import br.edu.ifpb.pweb2.aguiamaster.service.PeriodoLetivoService;

@Controller
@RequestMapping("/declaracao")
public class DeclaracaoController {
    @Autowired
    DeclaracaoService declaracaoService;

    @Autowired
    EstudanteService estudanteService;

    @Autowired
    DeclaracaoRepository declaracaoRepository;

    @Autowired
    DocumentoService documentoService;

    @Autowired
    InstituicaoService instituicaoService;

    @Autowired
    PeriodoLetivoService periodoLetivoService;

    @RequestMapping("/form")
    public ModelAndView getCadastroDecaracao(Declaracao declaracao ,ModelAndView mav){
    mav.addObject("declaracao", declaracao);
    mav.setViewName("declaracao/form");
    mav.addObject("titulo", "Cadastro Declaração");
    return mav;

    }
   

    @ModelAttribute("estudantes")
    public List<Estudante> getEstudantes() {
        return estudanteService.getEstudantes();
    }
    // @ModelAttribute("estudanteIns")
    // public  List<PeriodoLetivo> getPeriodos(@RequestParam("id") Integer id){
    //      Estudante es = estudanteService.getEstudanteById(id);
    //      Instituicao is = instituicaoService.getInstituicaoById(es.getInstituicao().getId());
    //      return instituicaoService.getPeriodoInstituicaoById(is);
      

    //  }
  

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listAll(ModelAndView mav) {
        mav.setViewName("declaracao/list");
        mav.addObject("declaracoes", declaracaoService.getDeclaracao());
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(@Valid Declaracao declaracao,BindingResult validation,ModelAndView 
    mav,RedirectAttributes attrs){
         if (validation.hasErrors()) {
            mav.addObject("message", "Erros de validação! Corrija-os e tente novamente.");
            mav.setViewName("declaracao/form");
            return mav;
        }
        if (declaracao.getId() == null) {
            attrs.addFlashAttribute("message", "Declaracao cadastrada com sucesso!");

        } else {
            attrs.addFlashAttribute("message", "Declaracao editada com sucesso!");
        }
        declaracao.getEstudante().setDeclaracaoAtual(null);
        declaracao.getEstudante().setDeclaracaoAtual(declaracao);
        
        declaracaoService.salveDecaracao(declaracao);
        mav.setViewName("redirect:declaracao");

        return mav;
    }


    private Documento uploadArquivo(MultipartFile arquivo) throws IOException {
            String nomeArquivo = StringUtils.cleanPath(arquivo.getOriginalFilename());
            Documento documento = documentoService.grave(nomeArquivo, arquivo.getBytes());
            return documento;
    }
    private void linkDoc(Declaracao declaracao,Documento documento){
        documento.setUrl(buildUrl(declaracao.getId(), documento.getId()));
        declaracao.setDocumento(documento);
    }
    private String buildUrl(Integer idDeclaracao, Integer idDocumento) {
        String fileDownloadUri = ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .path("/declaracao/")
                .path(idDeclaracao.toString())
                .path("/documentos/")
                .path(idDocumento.toString())
                .toUriString();
        return fileDownloadUri;
    }
    @RequestMapping("/{id}/documentos/{idDoc}")
    public  ResponseEntity<byte[]> getDocumentos(@PathVariable("idDoc") Integer idDoc){
        Documento documento = documentoService.getDocumento(idDoc);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment=\""+documento.getNome()+"\"")
                .body(documento.getDados());
    }
    
    @RequestMapping("/{id}/documentos")
    public ResponseEntity<byte[]> getDocumentos(@PathVariable("id")Integer id,
            ModelAndView mav) {
        Optional<Documento> documento = documentoService.getDocumentoOf(id);
        if (documento.isPresent()) {
            Documento doc = documento.get();
            HttpHeaders headers = new HttpHeaders();
        
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", doc.getNome());

            return new ResponseEntity<>(doc.getDados(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @RequestMapping("/{id}")
    public ModelAndView getDeclaracaoById(@PathVariable(value = "id") Integer id, ModelAndView mav) {
        mav.addObject("declaracao", "declaracao");
        Optional<Declaracao> opDec = declaracaoService.getDeclaracaoById(id);
        if (opDec.isPresent()) {
            mav.setViewName("declaracoes/form");
            mav.addObject("declaracao", opDec.get());
        } else {
            mav.setViewName("declaracao/list");
            mav.addObject("message", "declaracao com id " + id + " não encontrado.");
        }
        return mav;
    }

     @RequestMapping("/excluir/{id}")
     public ModelAndView deleteById(@PathVariable("id") Integer id, ModelAndView mav, RedirectAttributes attr) {
        Optional<Declaracao> declaracao = declaracaoService.getDeclaracaoById(id);
        declaracao.get().setEstudante(null);
        declaracao.get().setPeriodoLetivo(null);
        declaracaoService.deleteDeclaracaoById(id);
        attr.addFlashAttribute("mensagem", "Declaracao removido com sucesso!");
         mav.setViewName("redirect:/declaracao");
         return mav;
     }
    @RequestMapping("/edita/{id}")
    public ModelAndView edita(@PathVariable("id") Integer id, ModelAndView mav, RedirectAttributes attr) {
        
       Optional<Declaracao> dec = declaracaoService.getDeclaracaoById(id);
        mav.addObject("declaracao", dec.get());
        mav.addObject("titulo", "Editando");
        mav.setViewName("declaracao/form");
        return mav;
    }
    @RequestMapping("/{id}/documentos/form")
    public ModelAndView getForm(ModelAndView mav, @PathVariable(name = "id") Integer id) {
        mav.addObject("id", id);
        mav.setViewName("declaracao/documentos/form");
        return mav;
    }

    @RequestMapping(value = "/{id}/documentos/upload", method = RequestMethod.POST)
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile arquivo,@PathVariable("id") Integer id, ModelAndView mav) {
    
        String mensagem = "";
        String proxPagina = "";
        try {
            
            Optional<Declaracao> opDeclaracao = declaracaoService.getDeclaracaoById(id);
            Declaracao declaracao = null;
            if (opDeclaracao.isPresent()) {
                declaracao = opDeclaracao.get();
                Documento documento = this.uploadArquivo(arquivo);
                this.linkDoc(declaracao, documento);
                declaracaoService.salveDecaracao(declaracao);
                mensagem = "Documento carregado com sucesso: " + arquivo.getOriginalFilename();
                proxPagina = String.format("redirect:/declaracao/%s/documentos", declaracao.getId().toString());
            }
        } catch (Exception e) {
            mensagem = "Não foi possível carregar o documento: " + arquivo.getOriginalFilename() + "! "
                    + e.getMessage();
            proxPagina = "/declaracao/documentos/form";
        }
        mav.addObject("mensagem", mensagem);
        mav.setViewName(proxPagina);
        return mav;
    }
    @ModelAttribute("periodoLetivoIns")
    public List<PeriodoLetivo> getPeriodoLetivos() {
        return estudanteService.getEstudantes().get(0).getInstituicao().getPeriodos();
    }

   
}

