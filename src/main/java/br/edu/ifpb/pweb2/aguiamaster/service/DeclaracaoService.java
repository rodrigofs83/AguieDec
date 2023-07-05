package br.edu.ifpb.pweb2.aguiamaster.service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.aguiamaster.model.Declaracao;
import br.edu.ifpb.pweb2.aguiamaster.repository.DeclaracaoRepository;

@Service
public class DeclaracaoService {
    @Autowired
    DeclaracaoRepository declaracaoRepository;
    
    @Transactional
    public Declaracao salveDecaracao(Declaracao novaDeclaracao) {
        // peg data atua
         LocalDate dataEnviorLocalDate = LocalDate.now();
         
        Date dataEnvior = Date.from(dataEnviorLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        novaDeclaracao.setDataRecebimento(dataEnvior);
        LocalDate dataVencLocalDate = dataEnviorLocalDate.plusDays(30);
        Date dataVenc = Date.from(dataVencLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        novaDeclaracao.setDataVencimento(dataVenc);
        Declaracao declaracao = this.declaracaoRepository.save(novaDeclaracao);
        return declaracao;
    }
    public List<Declaracao>  getDeclaracao() {
        return this.declaracaoRepository.findAll();
    }

    public Optional<Declaracao> getDeclaracaoById(Integer declaracao_id){
        return this.declaracaoRepository.findById(declaracao_id);

    }
    public void deleteDeclaracaoById(Integer id) {
        this.declaracaoRepository.deleteById(id);

    }
    //  public List<Declaracao> getDeclaracaByIdEs(Integer id){
    //     return declaracaoRepository.getDeclaracaoByIdEstudante(id);
    // }

    // public List<Declaracao> getDeclaracaoVec() {
    //     return declaracaoRepository.getDeclaracaoVec();
    // }

    // public List<Declaracao> getDeclaracaoVec(Integer qtddDias) {        
    //     List<Declaracao> declaracao = declaracaoRepository.declaracaoDias(qtddDias);
    //     return declaracao;
    // }
   
}
