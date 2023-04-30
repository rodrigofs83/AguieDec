package br.edu.ifpb.pweb2.aguiamaster.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.aguiamaster.model.Declaracao;
import br.edu.ifpb.pweb2.aguiamaster.repository.DeclaracaoRepository;

@Service
public class DeclaracaoService {
    @Autowired
    DeclaracaoRepository declaracaoRepository;
    public Declaracao salveDecaracao(Declaracao novaDeclaracao) {
        Declaracao declaracao = this.declaracaoRepository.save(novaDeclaracao);
        return declaracao;
    }
    public List<Declaracao>  getDeclaracao() {
        return this.declaracaoRepository.findAll();
    }
    
}
