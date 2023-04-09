package br.edu.ifpb.pweb2.aguiamaster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.aguiamaster.model.Instituicao;
import br.edu.ifpb.pweb2.aguiamaster.repository.InstituicaoRepository;

@Service
public class InstituicaoService {

    @Autowired
    InstituicaoRepository instituicaoRepository;

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

        this.instituicaoRepository.deleteById(instituicao_id);
    }

    
    
}
