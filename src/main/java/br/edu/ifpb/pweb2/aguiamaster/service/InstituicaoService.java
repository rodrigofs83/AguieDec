package br.edu.ifpb.pweb2.aguiamaster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.aguiamaster.model.Estudante;
import br.edu.ifpb.pweb2.aguiamaster.model.Instituicao;
import br.edu.ifpb.pweb2.aguiamaster.model.PeriodoLetivo;
import br.edu.ifpb.pweb2.aguiamaster.repository.InstituicaoRepository;

@Service
public class InstituicaoService {

    @Autowired
    InstituicaoRepository instituicaoRepository;

    @Autowired
    PeriodoLetivoService periodoLetivoService;

    @Autowired
    EstudanteService estudanteService;

    public List<Instituicao> getInstituicao() {
        return this.instituicaoRepository.findAll();
    } 

    public Instituicao saveInstituicao(Instituicao novoInstituicao){

        Instituicao instituicao = this.instituicaoRepository.save(novoInstituicao);
        return instituicao;        

    }



    public Instituicao getInstituicaoById(Integer instituicao_id) {

        return  this.instituicaoRepository.findById(instituicao_id).orElse(null);
    }


    public void deleteInstituicaoById(Integer instituicao_id) {
        Instituicao ins = this.getInstituicaoById(instituicao_id);
        for(PeriodoLetivo pl : periodoLetivoService.getPeriodoLetivos()){
            if(pl.getInstituicao().getId() == instituicao_id){
                pl.setInstituicao(null);
                
            }
        }
        for(Estudante es : estudanteService.getEstudantes()){
                if(es.getInstituicao().getId() == instituicao_id){
                es.setInstituicao(null);
                
            }
        }
        this.instituicaoRepository.deleteById(instituicao_id);
    }

    
    
}
