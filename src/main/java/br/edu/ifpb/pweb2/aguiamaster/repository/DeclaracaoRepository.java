package br.edu.ifpb.pweb2.aguiamaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import br.edu.ifpb.pweb2.aguiamaster.model.Declaracao;
import br.edu.ifpb.pweb2.aguiamaster.model.Documento;

@Component
public interface DeclaracaoRepository extends JpaRepository<Declaracao,Integer>{

    @Query(value = "select d.documento from Declaracao d where d.id = :idDeclaracao")
    Documento findDocumentoById(@Param ("idDeclaracao") Integer idDeclaracao);
    // List<Declaracao> getDeclaracaoByIdEstudante(Integer id);
    
    //  @Query(value = "select d.declaracao from estudante es where es.id = :idDeclaracao")
    //    List<Declaracao> getDeclaracaByIdEs(@Param ("id") Integer idDeclaracao);

    // List<Declaracao> getDeclaracaoVec();

    // List<Declaracao> declaracaoDias(Integer qtddDias);

    

    
}
