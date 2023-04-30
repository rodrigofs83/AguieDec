package br.edu.ifpb.pweb2.aguiamaster.repository;

import org.hibernate.type.IntegerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.edu.ifpb.pweb2.aguiamaster.model.Declaracao;

@Component
public interface DeclaracaoRepository extends JpaRepository<Declaracao, Integer>{
    
}
